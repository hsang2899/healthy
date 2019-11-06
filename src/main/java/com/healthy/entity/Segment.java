package com.healthy.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "segment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Segment implements Serializable{
  private static final long serialVersionUID = -5914340024630290766L;

    @Id
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "email",length = 255)
    private String email;
}
