package com.course.work.hostelserver.service;

import com.course.work.hostelserver.entity.Guest;
import com.course.work.hostelserver.repository.ResidenceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResidenceServiceImpl implements ResidenceService {

    @Resource
    private ResidenceRepository repository;

    @Override
    public void createResidence(Guest guest) {
         repository.createResidence(guest);
    }

}
