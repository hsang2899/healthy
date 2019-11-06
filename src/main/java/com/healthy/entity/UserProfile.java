package com.healthy.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profile")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserProfile implements Serializable {
  private static final long serialVersionUID = -8114196517540057817L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_profile_id")
  private Long userProfileId;

  @OneToOne
  @JoinColumn(name = "account_id", unique = true)
  private Account account;

  @Column(name = "full_name",length = 255)
  private String fullName;

  @Column(name = "gender",length = 255)
  private String gender;

  @Column(name = "date_of_birth")
  private Long dateOfBirth;

  @Column(name = "phone",length = 255)
  private String phone;

  @Column(name = "weight")
  private Float weight;

  @Column(name = "height")
  private Float height;

  @Column(name = "avatar_url", columnDefinition = "text")
  private String avatarUrl;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = CascadeType.ALL)
  private Nutrition nutrition;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
