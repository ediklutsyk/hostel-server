package com.course.work.hostelserver.service;

import com.course.work.hostelserver.repository.CleanerRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class CleanerServiceImpl implements CleanerService {

    @Resource
    CleanerRepository repository;

    @Override
    public List<Map<String, Object>> findAll() {
        return repository.findAll();
    }

}
