package com.course.work.hostelserver.repository;

import java.util.List;
import java.util.Map;

public interface CleanerRepository {
    List<Map<String, Object>> findAll();
}
