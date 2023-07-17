package uz.tashkent.epam.cources.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.tashkent.epam.cources.dto.EmployeeDTO;
import uz.tashkent.epam.cources.model.Employee;

import java.util.List;

public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final String GET_ALL_EMPLOYEES = "SELECT employee_id, first_name ||' '|| last_name as full_name, email, start_date, department_id " +
            "FROM EMPLOYEE ORDER BY full_name";

    private final String GET_ALL_EMPLOYEES_BY_DEP_ID = "SELECT employee_id, first_name ||' '|| last_name as full_name, email, start_date, department_id " +
            "FROM EMPLOYEE " +
            "WHERE department_id = ? " +
            "ORDER BY full_name";

    JdbcTemplate jdbcTemplate;

    public EmployeeDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = jdbcTemplate.query(GET_ALL_EMPLOYEES, BeanPropertyRowMapper.newInstance(EmployeeDTO.class));
        return employees;
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Integer departmentId) {
        List<EmployeeDTO> employees =
                jdbcTemplate.query(GET_ALL_EMPLOYEES_BY_DEP_ID, new Object[]{departmentId}, BeanPropertyRowMapper.newInstance(EmployeeDTO.class));
        return employees;
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
