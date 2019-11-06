package com.healthy.service.impl;

import com.healthy.entity.Segment;
import com.healthy.repository.SegmentRepository;
import com.healthy.service.SegmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentServiceImpl implements SegmentService {
    @Autowired
    SegmentRepository segmentRepository;

    @Override
    public Segment createSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    @Override
    public Segment deleteSegment(Long playerId) {
        Segment segment = segmentRepository.findByPlayerId(playerId);
        segmentRepository.delete(segment);
        return segment;
    }

    @Override
    public List<Long> getListPlayerIdByEmail(String email) {
      return segmentRepository.findPlayerIdByEmail(email);
    }
}
