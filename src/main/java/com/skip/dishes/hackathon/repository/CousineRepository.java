package com.skip.dishes.hackathon.repository;

import com.skip.dishes.hackathon.model.Cousine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CousineRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public static final RowMapper<Cousine> ROW_MAPPER = new RowMapper<Cousine>() {
        @Nullable
        @Override
        public Cousine mapRow(ResultSet rs, int i) throws SQLException {

            Cousine cousine = new Cousine();
            cousine.setId(rs.getLong("cousine.id"));
            cousine.setName(rs.getString("cousine.name"));
            return cousine;
        }
    };

    public List<Cousine> findBySearch(String search) {
        try {
            return jdbcTemplate.query("SELECT * FROM cousine AS cousine WHERE LOWER(cousine.name) LIKE ?",
                    new Object[]{"%" + search.toLowerCase() + "%"}, ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Cousine save(Cousine cousine) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", cousine.getName());
            Long id = (Long) new SimpleJdbcInsert(jdbcTemplate).withTableName("cousine").usingGeneratedKeyColumns("id").executeAndReturnKey(parameters);

            cousine.setId(id);

            return cousine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cousine> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM cousine AS cousine", ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
