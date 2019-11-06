package com.healthy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "day", uniqueConstraints = @UniqueConstraint(columnNames = { "date", "account_id" }))
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Day implements Serializable {
	private static final long serialVersionUID = 2771055567317840583L;
	@Id
	@Column(name = "day_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dayId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@Column(name = "date")
	private Long date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "break_fast_id")
	private Meal breakFast;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lunch_id")
	private Meal lunch;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dinner_id")
	private Meal dinner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "snacks_id")
	private Meal snacks;

	@Column(name = "createdAt")
	private Long createdAt;

	@Column(name = "updatedAt")
	private Long updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exercise_day_id")
	private ExerciseDay exerciseDay;
}
