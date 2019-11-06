package com.healthy.service;

import java.util.List;
import com.healthy.entity.Segment;

public interface SegmentService {
    Segment createSegment(Segment segment);

    Segment deleteSegment(Long playerId);
    
    List<Long> getListPlayerIdByEmail(String email);
}
