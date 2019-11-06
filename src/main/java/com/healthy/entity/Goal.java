package com.healthy.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "goal")
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Goal implements Serializable{
	private static final long serialVersionUID = -5839235872488973375L;

	@Id
    @Column(name = "email", nullable = false,length = 255)
    private String email;

    @Column(name = "calories")
    private double calories;

    @Column(name = "proteins")
    private double proteins;

    @Column(name = "carbon")
    private double carbon;

    @Column(name = "fat")
    private double fat;

    @Column(name = "weight_start")
    private double weightStart;

    @Column(name = "weight")
    private double weight;

    @Column(name = "weight_finish")
    private double weightFinish;

    @Column(name = "month")
    private Integer month;

    @Column(name = "type")
    private Integer type;

    @Column(name = "activity_level")
    private Integer activityLevel; // 1, 2, 3

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "createdAt")
    private Long createdAt;

    @Column(name = "updatedAt")
    private Long updatedAt;
}
