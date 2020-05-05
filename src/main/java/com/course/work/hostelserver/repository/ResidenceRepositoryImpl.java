package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.Guest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ResidenceRepositoryImpl implements ResidenceRepository {

    public ResidenceRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public void createResidence(Guest guest) {
        String sql = "INSERT INTO residence(room_id, guest_id, check_in, check_out) " +
                "values (:roomId," +
                " (SELECT guest_id FROM guest ORDER BY guest_id DESC LIMIT 1)," +
                " NOW(), " +
                " NOW() + :checkOut::interval); ";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("roomId", guest.getRoom())
                .addValue("checkOut", guest.getDays() + " days");
        template.update(sql, param, holder);
    }
}

