package com.course.work.hostelserver.controller;

import com.course.work.hostelserver.entity.Guest;
import com.course.work.hostelserver.service.GuestService;
import com.course.work.hostelserver.service.ResidenceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Resource
    private GuestService service;

    @Resource
    private ResidenceService residenceService;

    @GetMapping
    public List<Map<String, Object>> getAllGuests() {
        return service.findAll();
    }

    @GetMapping(value = "/id")
    public List<Map<String, Object>> getById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/search")
    public List<Map<String, Object>> getGuestBySurnameLike(@RequestParam String surname) {
        return service.findLikeSurname(surname);
    }

    @GetMapping(value = "/active")
    public List<Map<String, Object>> getActiveGuests() {
        return service.findActive();
    }

    @GetMapping(value = "/prices")
    public List<Map<String, Object>> getGuestsWithPrices() {
        return service.getGuestsWithPrices();
    }

    @PostMapping
    public void getGuestsWithPrices(@RequestBody Guest guest) {
        service.createGuest(guest);
        residenceService.createResidence(guest);
    }

    @GetMapping(value = "/phone")
    public List<Map<String, Object>> checkPhone(@RequestParam String phone) {
        return service.checkPhone(phone);
    }

}
