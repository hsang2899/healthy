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
@Table(name = "exercise")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Exercise implements Serializable {
	private static final long serialVersionUID = -2468268952687026027L;

	@Id
	@Column(name = "exercise_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exerciseId;

	@Column(name = "index_of_sets")
	private Integer indexOfSets;

	@Column(name = "repetitions")
	private Integer repetitions;

	@Column(name = "weight_per_set")
	private Double weightPerSet;

	@Column(name = "calories_burn")
	private Double caloriesBurn;

	@Column(name = "exercise_name",length = 255)
	private String exerciseName;

	@Column(name = "video_url", columnDefinition = "text")
	private String videoUrl;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "owner",length = 255)
	private String owner;

	@Column(name = "updated_at")
	private Long updatedAt;

	@Column(name = "created_at")
	private Long createdAt;

	@OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ExerciseDetail> exerciseDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
}
