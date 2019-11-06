package com.healthy.service.impl;

import com.healthy.entity.Goal;
import com.healthy.repository.GoalRepository;
import com.healthy.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    GoalRepository goalRepository;

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> searchGoal() {
        return goalRepository.findAll();
    }

    @Override
    public Goal getGoal(String email) {
        return goalRepository.findByEmail(email);
    }
}
