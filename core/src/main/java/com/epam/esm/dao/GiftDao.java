package com.epam.esm.dao;

import com.epam.esm.converter.DateConverter;
import com.epam.esm.entity.GiftSertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

@Component
public class GiftDao {
    private JdbcTemplate template;

    @Autowired
    public GiftDao(DataSource source) {
        this.template = new JdbcTemplate(source);
    }

    private RowMapper<GiftSertificate> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new GiftSertificate(resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                new DateConverter().formatDate(resultSet.getTimestamp("creation_date")),
                new DateConverter().formatDate(resultSet.getTimestamp("last_update_time")),
                resultSet.getShort("duration"));
    };

    public List<GiftSertificate> getAll() {
        return template.query("select * from Certificates", ROW_MAPPER);
    }


    public GiftSertificate getById(Integer id) {
        return template.queryForObject("select * from Certificates where id = ?", new Object[]{id}, (rs, rowNum) ->
                new GiftSertificate(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        new DateConverter().formatDate(rs.getTimestamp("creation_date")),
                        new DateConverter().formatDate(rs.getTimestamp("last_update_time")),
                        rs.getShort("duration"))
        );
    }


    public boolean save(GiftSertificate entity) {
        String sql = "insert into Certificates(title,description,price,creation_date,last_update_time,duration) values (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = template.update(new PreparedStatementCreator() {
             public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                 PreparedStatement pst =
                         con.prepareStatement(sql, new String[]{"id"});
                 pst.setString(1, entity.getName());
                 pst.setString(2, entity.getDescription());
                 pst.setDouble(3, entity.getPrice());
                 pst.setTimestamp(4, new Timestamp(new Date().getTime()));
                 pst.setTimestamp(5, new Timestamp(new Date().getTime()));
                 pst.setShort(6, entity.getDuration());
                 return pst;
             }
         }, keyHolder);
        System.out.println(keyHolder.getKey());
        //entity.getName(), entity.getDescription(), entity.getPrice(), new Timestamp(new DateConverter().parse(entity.getCreationDate()).getTime()), new Timestamp(new Date().getTime()), entity.getDuration())
        entity.getTags().forEach(el -> {

        });
        //add insert to connection table if exists
        return result > 0;
    }


    public boolean delete(Integer id) {
        return template.update("delete from certificates where id = ?", id) != 0;
    }


    public GiftSertificate update(Integer id, GiftSertificate newObj) {
        template.update("update certificates set title = ?,description = ?,price=?,last_update_time = ?,duration = ? where id= ?", newObj.getName(), newObj.getDescription(), newObj.getPrice(), new Timestamp(new Date().getTime()), newObj.getDuration(), id);
        return newObj;
    }

}
