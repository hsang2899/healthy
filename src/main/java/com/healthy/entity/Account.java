package com.healthy.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Getter
@Setter
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account implements Serializable {
  private static final long serialVersionUID = 8860361382068679126L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id", nullable = false)
  private Long accountId;

  @Column(name = "user_name",unique = true,length = 255)
  private String userName;

  @Column(name = "password",length = 255)
  private String password;

  @Column(name = "email",unique = true,length = 255)
  private String email;

  @Column(name = "status",length = 255)
  private String status;

  @Column(name = "role",length = 255)
  private String role;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
  private UserProfile userProfile;

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Food> foods;
  
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Exercise> exercises;
  
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Day> days;
  
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Weight> weights;
  
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Goal> goals;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
