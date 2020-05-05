package com.course.work.hostelserver.service;

import java.util.List;
import java.util.Map;

public interface ServicesService {
    List<Map<String, Object>> filter(Double price, Integer amount);

    List<Map<String, Object>> servicesById(Integer id);

    List<Map<String, Object>> servicesSumById(Integer id);
}
