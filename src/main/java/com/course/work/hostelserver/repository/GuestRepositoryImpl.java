package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.Guest;
import com.course.work.hostelserver.mapper.ObjectRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GuestRepositoryImpl implements GuestRepository {

    public GuestRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public List<Map<String, Object>> findAll() {
        return template.query("SELECT * FROM guest;", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findLikeSurname(String surname) {
        return template.query("SELECT * FROM guest WHERE LOWER(surname) LIKE LOWER(:surname);",
                Collections.singletonMap("surname", surname + "%"),
                new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findActive() {
        return template.query("SELECT g.guest_id, g.name, g.surname, res.check_out " +
                        "FROM guest g " +
                        "INNER JOIN residence res ON g.guest_id = res.guest_id " +
                        "WHERE NOW() BETWEEN res.check_in AND res.check_out;",
                new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> getGuestsWithPrices() {
        return template.query("SELECT g.guest_id, g.name, g.surname, SUM(s.price) AS sum_price " +
                        "FROM services s " +
                        "INNER JOIN guest g on g.guest_id = s.guest_id " +
                        "GROUP BY g.guest_id " +
                        "ORDER BY g.guest_id;",
                new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findById(Integer id) {
        return template.query("SELECT * FROM guest WHERE guest_id = :id;",
                Collections.singletonMap("id", id), new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> checkIfPhoneExist(String phone) {
        return template.query("SELECT EXISTS(SELECT 1 FROM guest WHERE phone = :phone);",
                Collections.singletonMap("phone", phone), new ObjectRowMapper());
    }

    @Override
    public void createGuest(Guest guest) {
        final String sql = "INSERT INTO guest(\"name\", surname, email, phone) " +
                "values(:guestName,:surname,:phone,:email)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("guestName", guest.getName())
                .addValue("surname", guest.getSurname())
                .addValue("phone", guest.getPhone())
                .addValue("email", guest.getEmail());
        template.update(sql, param, holder);

    }


}

