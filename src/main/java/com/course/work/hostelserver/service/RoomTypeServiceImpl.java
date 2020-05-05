package com.course.work.hostelserver.service;

import com.course.work.hostelserver.repository.RoomTypeRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class RoomTypeServiceImpl implements RoomTypeService {
    @Resource
    RoomTypeRepository repository;

    @Override
    public List<Map<String, Object>> getAmountOfRoomsByEachType() {
        return repository.getAmountOfRoomsByEachType();
    }
}
