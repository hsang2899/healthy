package com.healthy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.healthy.entity.Account;
import com.healthy.entity.Notify;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.MessageDto;
import com.healthy.model.NotifyDto;
import com.healthy.model.NotifySendDto;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.service.NotifyService;
import com.healthy.service.SegmentService;

@RestController
@RequestMapping("/api/notify")
public class NotifyController {
  @Autowired
  AccountRepository accountRepository;
  
  @Autowired
  private NotifyService notifyService;
  
  @Autowired
  private SegmentService segmentService;
  
  @PostMapping(value = "/")
  public ResponseObject<DTOEntity> sendNotify(@RequestBody NotifyDto notifyDto, HttpServletRequest request) {
      String rsEmail = request.getAttribute("rsEmail").toString();
      Account account = accountRepository.findByEmail(rsEmail);
      if(account.getRole().equals("admin")) {
        Notify notify = (Notify) DtoUtils.convertToEntity(new Notify(), notifyDto);
        notifyService.saveNotify(notify);
        final String uri = "https://onesignal.com/api/v1/notifications";
        final String appId = "6d75dce6-cc16-48c6-a0d6-6e5a38a47cff";
        final String token = "Basic MDg2YjkwM2MtZmQ2MS00OGM3LThkM2YtODYxODgzYmQ0ZTI3";
        List<Long> playerIdList = segmentService.getListPlayerIdByEmail(notifyDto.getEmail());
        MessageDto headings = MessageDto.builder().message(notifyDto.getTitle()).build();
        MessageDto content = MessageDto.builder().message(notifyDto.getMessage()).build();
        NotifySendDto notifySendDto = NotifySendDto.builder().appId(appId).contents(content).headings(headings).includePlayerIds(playerIdList).build();
     
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("author", token);
        HttpEntity<NotifySendDto> requestEntity = 
            new HttpEntity<>(notifySendDto, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(uri, HttpMethod.POST, requestEntity, NotifySendDto.class);
        
        return new ResponseObject<>(null, true, "success!");
      }
      return new ResponseObject<>(null, false, "You must be adminstrator!");
  }
  
  @GetMapping(value = "/")
  public ResponseObject<List<DTOEntity>> sendNotify( HttpServletRequest request) {
      String rsEmail = request.getAttribute("rsEmail").toString();
      Account account = accountRepository.findByEmail(rsEmail);
      List<DTOEntity> listFood = notifyService.getAllByEmail(account.getEmail()).stream()
          .map(entity -> DtoUtils.convertToDto(entity, new NotifyDto())).collect(Collectors.toList());
      return new ResponseObject<>(listFood, true, "Success!");
  }
}
