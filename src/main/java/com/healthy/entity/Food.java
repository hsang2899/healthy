package com.healthy.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "food", indexes = {@Index(name = "IDX_FOOD_NAME", columnList = "food_name")})
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Food implements Serializable {
  private static final long serialVersionUID = -1147639925385380503L;
  @Id
  @Column(name = "food_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long foodId;

  @Column(name = "food_name", unique = true,length = 255)
  private String foodName;

  @Column(name = "description",length = 255)
  private String description;

  @Column(name = "unit",length = 255)
  private String unit;

  @Column(name = "calories")
  private Double calories;

  @Column(name = "proteins")
  private Double proteins;

  @Column(name = "carbs")
  private Double carbs;

  @Column(name = "fat")
  private Double fat;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "status")
  private Boolean status;

  @OneToMany(mappedBy = "food")
  private List<MealDetail> mealDetails;

  @Column(name = "owner",length = 255)
  private String owner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @Column(name = "createdAt")
  private Long createdAt;

  @Column(name = "updatedAt")
  private Long updatedAt;
}
