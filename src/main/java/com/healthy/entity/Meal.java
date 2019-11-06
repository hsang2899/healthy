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
@Table(name = "meal")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Meal implements Serializable {
  private static final long serialVersionUID = 9185513397109860934L;
  @Id
  @Column(name = "meal_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mealId;

  @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<MealDetail> mealDetails;

  @Column(name = "status",length = 255)
  private String status;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
