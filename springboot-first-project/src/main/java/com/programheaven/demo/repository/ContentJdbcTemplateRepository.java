package com.programheaven.demo.repository;

import com.programheaven.demo.model.Content;
import com.programheaven.demo.model.Status;
import com.programheaven.demo.model.Type;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;


    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated",LocalDateTime.class),
                rs.getString("url"));
    }

    public List<Content> getAllContent(){
        String sql = "SELECT * FROM Content";
        System.out.println("Showing it");

        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }


    public void createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status.toString(), contentType.toString(), URL);
    }

    public void updateContent(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Content getContent(int id) {
        try {
            String sql = "SELECT * FROM Content WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        } catch (EmptyResultDataAccessException e) {
            // Handle empty result - throw a custom exception or return null
            return null; // Or throw a custom exception
        }
    }
}
