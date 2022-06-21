package uz.tashkent.epam.cources.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import uz.tashkent.epam.cources.model.Department;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDaoJdbc {

    private static final String SQL_GET_ALL_DEPARTMENTS
            = "select departmentId, departmentName from departments order by ...";

    String url = "";
    String username = "";
    String password = "";

    DataSource dataSource = new DriverManagerDataSource(url, username, password);

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = jdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new DepartmentRowMapper());
        return departments;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer departmentId = rs.getInt("departmentId");
            String departmentName = rs.getString("departmentName");
            String departmentDescription = "description";
            return new Department(departmentId, departmentName, departmentDescription);
        }
    }
}
