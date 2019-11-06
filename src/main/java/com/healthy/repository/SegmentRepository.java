package com.healthy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.healthy.entity.Segment;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, String> {
    Segment findByPlayerId(Long playerId);
    
    @Query(value = "SELECT player_id FROM segment WHERE email = :email", nativeQuery = true)
    List<Long> findPlayerIdByEmail(@Param("email") String email);
}
