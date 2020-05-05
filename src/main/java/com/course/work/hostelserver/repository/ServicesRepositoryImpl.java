package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.mapper.ObjectRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ServicesRepositoryImpl implements ServiceRepository {

    public ServicesRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public List<Map<String, Object>> findAllWithPriceMore(Double price) {
        return template.query("SELECT g.guest_id, g.name, g.surname, SUM(s.price) AS sum_price " +
                        "FROM services s " +
                        "INNER JOIN guest g on g.guest_id = s.guest_id " +
                        "GROUP BY g.guest_id " +
                        "HAVING SUM(s.price) > :price " +
                        "ORDER BY g.guest_id;",
                Collections.singletonMap("price", price)
                , new ObjectRowMapper());
    }


    @Override
    public List<Map<String, Object>> findAllWithServicesAmount(Integer amount) {
        return template.query("SELECT g.guest_id, g.name, g.surname, COUNT(s.service_id) AS amount " +
                        "FROM services s " +
                        "INNER JOIN guest g on g.guest_id = s.guest_id " +
                        "GROUP BY g.guest_id " +
                        "HAVING COUNT(s.service_id) > :amount " +
                        "ORDER BY g.guest_id;",
                Collections.singletonMap("amount", amount)
                , new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findServicesByGuest(Integer id) {
        return template.query("SELECT st.service_type_name, SUM(s.price) AS type_sum " +
                        "FROM services s " +
                        "INNER JOIN service_type st on s.service_type_id = st.service_type_id " +
                        "WHERE s.guest_id = :id " +
                        "GROUP BY st.service_type_name;",
                Collections.singletonMap("id", id)
                , new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findServicesSumByGuest(Integer id) {
        return template.query("SELECT SUM(price) AS guest_sum " +
                        "FROM services " +
                        "WHERE guest_id = :id;",
                Collections.singletonMap("id", id)
                , new ObjectRowMapper());
    }
}

