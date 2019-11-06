package com.healthy.entity;

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
@Table(name = "exercise_day")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ExerciseDay {
	@Id
	@Column(name = "exercise_day_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exerciseDayId;
	@Column(name = "status",length = 255)
	private String status;
	@OneToMany(mappedBy = "exerciseDay", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ExerciseDetail> exerciseDetails;
}
