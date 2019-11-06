package com.healthy.service;

import java.util.List;
import com.healthy.entity.Notify;

public interface NotifyService {
  Notify saveNotify(Notify notify);
  List<Notify> getAllByEmail(String email);
}
