package io.github.zp1024.spring.data.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class MyJdbcTemplate implements StudentDAO {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(String name, Integer age) {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplate.update(SQL, name, age);
        logger.debug("Created Record Name = " + name + " Age = " + age);
    }

    public StudentDTO getById(Integer id) {
        String SQL = "select * from Student where id = ?";
        StudentDTO student =
                        jdbcTemplate.queryForObject(SQL, new Object[] {id}, new StudentMapper());
        logger.debug("查到Student记录：{}", student.toString());
        return student;
    }

    public List<StudentDTO> list() {
        String SQL = "select * from Student";
        List<StudentDTO> students = jdbcTemplate.query(SQL, new StudentMapper());
        return students;
    }

    public void delete(Integer id) {
        String SQL = "delete from Student where id = ?";
        jdbcTemplate.update(SQL, id);
        logger.debug("Deleted Record with ID = " + id);
    }

    public void update(Integer id, Integer age) {
        String SQL = "update Student set age = ? where id = ?";
        jdbcTemplate.update(SQL, age, id);
        logger.debug("Updated Record with ID = " + id);
    }
}
