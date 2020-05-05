package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.mapper.ObjectRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.misc.Cleaner;

import java.util.List;
import java.util.Map;

@Repository
public class CleanerRepositoryImpl implements CleanerRepository {

    public CleanerRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public List<Map<String, Object>> findAll() {
        return template.query("SELECT * FROM cleaner;", new ObjectRowMapper());
    }
}

