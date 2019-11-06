package com.healthy.controller;

import com.healthy.api.SegmentApi;
import com.healthy.entity.Account;
import com.healthy.entity.Segment;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.ResponseObject;
import com.healthy.model.SegmentDto;
import com.healthy.repository.AccountRepository;
import com.healthy.repository.SegmentRepository;
import com.healthy.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController("SegmentController")
public class SegmentController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SegmentService segmentService;

    @Autowired
    SegmentRepository segmentRepo;

    @PostMapping(value = "/segment/new")
    @ResponseBody
    public ResponseObject<Segment> register(@RequestBody SegmentDto segmentDto, HttpServletRequest request) {
        String rsEmail = request.getAttribute("rsEmail").toString();
        Segment segment = (Segment) DtoUtils.convertToEntity(new Segment(), segmentDto);
        segment.setEmail(rsEmail);
        Segment newSegment = segmentService.createSegment(segment);
        return new ResponseObject<>(newSegment, true, "success!");
    }

    @PostMapping(value = "/segment/delete")
    @ResponseBody
    public SegmentApi.Response deleteSegment(@RequestBody SegmentApi.Request req, HttpServletRequest request) {
        System.out.println("start delete segment.");
        String rsEmail = request.getAttribute("rsEmail").toString();
        Account account = accountRepository.findByEmail(rsEmail);
        Long id = req.getPlayer_id();
        System.out.println("is delete: " + id);
        Segment segment = segmentRepo.findByPlayerId(id);
        System.out.println("get segment by id: " + segment);
        if (segment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment Not Found");
        }
        if (account.getRole().equals("admin") || rsEmail.equals(account.getEmail())) {
            segmentService.deleteSegment(id);
        }

        SegmentApi.Response response = new SegmentApi.Response();
        response.setStatus(true);
        response.setTitle("delete success!");
        return response;
    }
}
