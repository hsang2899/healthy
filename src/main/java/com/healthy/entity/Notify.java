package com.healthy.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notify")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Notify implements Serializable{
  private static final long serialVersionUID = 2685848032606450064L;

  @Id
  @Column(name = "notify_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notifyId;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "created_at")
  private Long createdAt;
  
  @Column(name = "title")
  private String title;
  
  @Column(name = "message", columnDefinition = "text")
  private String message;
}
