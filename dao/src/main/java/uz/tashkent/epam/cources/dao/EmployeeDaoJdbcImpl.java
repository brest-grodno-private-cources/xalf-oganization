package uz.tashkent.epam.cources.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import uz.tashkent.epam.cources.model.Employee;

import java.util.List;

public class EmployeeDaoJdbcImpl implements EmployeeDao {

    JdbcTemplate jdbcTemplate;

    public EmployeeDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Integer departmentId) {
        return null;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, String email, Integer departmentId) {
        return null;
    }

    @Override
    public int updateEmployee(String firstName, String lastName, String email) {
        return 0;
    }

    @Override
    public int deleteEmployee(Integer employeeId) {
        return 0;
    }

    @Override
    public void moveEmployeeToDepartment(Integer employeeId, Integer departmentId) {

    }
}
