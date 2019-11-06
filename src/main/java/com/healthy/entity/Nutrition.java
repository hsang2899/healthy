package com.healthy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Builder
@Table(name = "nutrition")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Nutrition implements Serializable {
  private static final long serialVersionUID = 8338513426409598213L;
  @Id
  @Column(name = "nutrition_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long nutritionId;

  @OneToOne
  @JoinColumn(name = "user_profile_id")
  private UserProfile userProfile;

  @Column(name = "weight_target")
  private Float weightTarget;

  @Column(name = "activity_level")
  private Integer activityLevel;

  @Column(name = "month_target")
  private Integer monthTarget;

  @Column(name = "started_date")
  private Date startedDate;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
