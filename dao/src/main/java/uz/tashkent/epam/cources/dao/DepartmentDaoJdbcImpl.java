package uz.tashkent.epam.cources.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import uz.tashkent.epam.cources.model.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private static final String SQL_GET_ALL_DEPARTMENTS
            = "select department_id, department_name from department order by department_name";

    JdbcTemplate jdbcTemplate;
    public DepartmentDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = jdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department addDepartment(String departmentName, String departmentDescription) {
        return null;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer departmentId = rs.getInt("department_id");
            String departmentName = rs.getString("department_name");
            String departmentDescription = "department_description";
            return new Department(departmentId, departmentName, departmentDescription);
        }
    }
}
