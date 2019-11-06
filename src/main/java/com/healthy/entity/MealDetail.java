package com.healthy.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "meal_detail")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MealDetail implements Serializable {
  private static final long serialVersionUID = -2369683169440639083L;
  @Id
  @Column(name = "meal_detail_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mealDetailId;

  @ManyToOne()
  @JoinColumn(name = "meal_id", nullable = false)
  private Meal meal;

  @ManyToOne()
  @JoinColumn(name = "food_id", nullable = false)
  private Food food;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
