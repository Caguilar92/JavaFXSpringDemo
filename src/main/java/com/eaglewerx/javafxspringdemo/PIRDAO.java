package com.eaglewerx.javafxspringdemo;

import com.eaglewerx.PIR;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PIRDAO {
    private JdbcTemplate jdbcTemplate;

    public PIRDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * when quering use row mapper to mapp result set columns to fields in the object
     */
    private RowMapper<PIR> rowMapper = new RowMapper<PIR>() {

        @Override
        public PIR mapRow(ResultSet rs, int rowNum) throws SQLException {
            PIR pir = new PIR();
            pir.setId(rs.getInt("id"));// name of db table column
            pir.setName(rs.getString("name"));//name of db table column
            return pir;
        }
    };


    public void saveNewPIR(PIR pir) {
        String sql = "INSERT INTO PIR (name) VALUES (?)";
        jdbcTemplate.update(sql,pir.getName());
    }
    //gets a single PIR, this is where the rowmapper gets used
    public PIR getPIRById(int id) {
        String sql = "SELECT id, name FROM PIR WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public List<PIR> getAllPIRs() {
        String sql = "SELECT * FROM PIR";
        return jdbcTemplate.query(sql,rowMapper);
    }
}
