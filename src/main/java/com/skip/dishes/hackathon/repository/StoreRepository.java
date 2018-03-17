package com.skip.dishes.hackathon.repository;

import com.skip.dishes.hackathon.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StoreRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public static final RowMapper<Store> ROW_MAPPER = new RowMapper<Store>() {
        @Nullable
        @Override
        public Store mapRow(ResultSet rs, int i) throws SQLException {

            Store store = new Store();
            store.setId(rs.getLong("store.id"));
            store.setName(rs.getString("store.name"));
            store.setAddress(rs.getString("store.address"));
            store.setCousineId(rs.getLong("store.cousine_id"));
            return store;
        }
    };

    public List<Store> findBySearch(String search) {
        try {
            return jdbcTemplate.query("SELECT * FROM store AS store WHERE LOWER(store.name) LIKE ?",
                    new Object[]{"%" + search + "%"}, ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Store save(Store store) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", store.getName());
            parameters.put("address", store.getAddress());
            parameters.put("cousine_id", store.getCousineId());
            Long id = (Long) new SimpleJdbcInsert(jdbcTemplate).withTableName("store").usingGeneratedKeyColumns("id").executeAndReturnKey(parameters);

            store.setId(id);

            return store;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Store> findByCousineId(Long cousineId) {
        try {
            return jdbcTemplate.query("SELECT store.id, store.name, store.address, store.cousine_id  FROM store AS store INNER JOIN cousine AS cousine ON store.cousine_id = cousine.id" +
                            " WHERE store.cousine_id = ?",
                    new Object[]{cousineId}, ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        SELECT * FROM skip.store as store INNER JOIN skip.cousine as cousine
//        ON store.cousine_id = cousine.id
//        WHERE cousine.id = 1
    }
}
