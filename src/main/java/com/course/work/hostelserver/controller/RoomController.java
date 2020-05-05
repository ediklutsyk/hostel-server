package com.course.work.hostelserver.controller;

import com.course.work.hostelserver.entity.FilterRooms;
import com.course.work.hostelserver.service.RoomServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Resource
    private RoomServiceImpl service;

    @GetMapping
    public List<Map<String, Object>> getRooms() {
        return service.findAll();
    }

    @GetMapping(value = "/private")
    public List<Map<String, Object>> getPrivate() {
        return service.findPrivate();
    }

    @GetMapping(value = "/private/guests")
    public List<Map<String, Object>> getPrivateByGuestNum(@RequestParam Integer amount) {
        return service.findPrivateForGuestNum(amount);
    }

    @GetMapping(value = "/dorms")
    public List<Map<String, Object>> getDorms() {
        return service.findDorms();
    }

    @GetMapping(value = "/dorms/guests")
    public List<Map<String, Object>> getDormsByGuestNum(@RequestParam Integer amount) {
        return service.findDormsForGuestNum(amount);
    }

    @PostMapping(value = "/filter")
    public List<Map<String, Object>> filter(@RequestBody FilterRooms filter) {
        return service.filter(filter);
    }

    @GetMapping(value = "/id")
    public List<Map<String, Object>> getById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/beds")
    public List<Map<String, Object>> getBeds() {
        return service.getAllBedsSizes();
    }

    @GetMapping(value = "/price-range")
    public List<Map<String, Object>> getPriceRange() {
        return service.getMinAndMaxPrice();
    }

    @GetMapping(value = "/cleaning")
    public List<Map<String, Object>> getAllCleaningByRoom(@RequestParam Integer id) {
        return service.findAllCleaningById(id);
    }

    @GetMapping(value = "/guests")
    public List<Map<String, Object>> getAllGuestsCurrentlyLiving(@RequestParam Integer id) {
        return service.findAllGuestCurrentlyLivingInTheRoom(id);
    }


}
