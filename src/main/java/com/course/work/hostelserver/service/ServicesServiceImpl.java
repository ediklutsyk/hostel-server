package com.course.work.hostelserver.service;

import com.course.work.hostelserver.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ServicesServiceImpl implements ServicesService {

    @Resource
    private ServiceRepository repository;

    @Override
    public List<Map<String, Object>> filter(Double price, Integer amount) {
        if (price != null && amount == null) {
            return repository.findAllWithPriceMore(price);
        }
        if (amount != null && price == null) {
            return repository.findAllWithServicesAmount(amount);
        }
        List<Map<String, Object>> listA = repository.findAllWithPriceMore(price);
        List<Map<String, Object>> listB = repository.findAllWithServicesAmount(amount);
        return listA.stream().filter(a -> idInList(listB, a.get("guestId"))).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> servicesById(Integer id) {
        return repository.findServicesByGuest(id);
    }

    @Override
    public List<Map<String, Object>> servicesSumById(Integer id) {
        return repository.findServicesSumByGuest(id);
    }


    private boolean idInList(List<Map<String, Object>> list, Object id) {
        return list.stream().anyMatch(e -> e.get("guestId").equals(id));
    }

}
