package com.course.work.hostelserver.service;

import com.course.work.hostelserver.entity.Guest;
import com.course.work.hostelserver.repository.GuestRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class GuestServiceImpl implements GuestService {

    @Resource
    private GuestRepository repository;

    @Override
    public List<Map<String, Object>> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Map<String, Object>> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Map<String, Object>> findLikeSurname(String surname) {
        return repository.findLikeSurname(surname);
    }

    @Override
    public List<Map<String, Object>> findActive() {
        return repository.findActive();
    }

    @Override
    public List<Map<String, Object>> getGuestsWithPrices() {
        return repository.getGuestsWithPrices();
    }

    @Override
    public List<Map<String, Object>> checkPhone(String phone) {
        return repository.checkIfPhoneExist(phone);
    }

    @Override
    public void createGuest(Guest guest) {
        repository.createGuest(guest);
    }


}
