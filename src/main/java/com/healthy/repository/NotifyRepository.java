package com.healthy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthy.entity.Notify;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, String> {
  List<Notify> findByEmail(String email);
}
