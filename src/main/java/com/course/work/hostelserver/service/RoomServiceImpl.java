package com.course.work.hostelserver.service;

import com.course.work.hostelserver.entity.FilterRooms;
import com.course.work.hostelserver.repository.RoomRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomRepository repository;

    @Override
    public List<Map<String, Object>> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Map<String, Object>> findPrivate() {
        return repository.findPrivate();
    }

    @Override
    public List<Map<String, Object>> findDorms() {
        return repository.findDorms();
    }

    @Override
    public List<Map<String, Object>> findPrivateForGuestNum(Integer amount) {
        return repository.findPrivateForGuestNum(amount);
    }

    @Override
    public List<Map<String, Object>> findDormsForGuestNum(Integer amount) {
        return repository.findDormsForGuestNum(amount);
    }

    @Override
    public List<Map<String, Object>> filter(FilterRooms filter ) {
        return repository.filterRooms(filter);
    }

    @Override
    public List<Map<String, Object>> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Map<String, Object>> getAllBedsSizes() {
        return repository.getAllBedsSizes();
    }

    @Override
    public List<Map<String, Object>> getMinAndMaxPrice() {
        return repository.getMinAndMaxPrice();
    }

    @Override
    public List<Map<String, Object>> findAllCleaningById(Integer id) {
        return repository.findAllCleaningById(id);
    }

    @Override
    public List<Map<String, Object>> findAllGuestCurrentlyLivingInTheRoom(Integer id) {
        return repository.findAllGuestCurrentlyLivingInTheRoom(id);
    }


}
