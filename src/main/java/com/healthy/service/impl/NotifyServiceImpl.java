package com.healthy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthy.entity.Notify;
import com.healthy.repository.NotifyRepository;
import com.healthy.service.NotifyService;

@Service
public class NotifyServiceImpl implements NotifyService{
  @Autowired
  private NotifyRepository notifyRepository;

  @Override
  public Notify saveNotify(Notify notify) {
    return notifyRepository.save(notify);
  }

  @Override
  public List<Notify> getAllByEmail(String email) {
    return notifyRepository.findByEmail(email);
  }

}
