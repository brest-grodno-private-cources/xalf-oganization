package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.dto.EmployeeDTO;
import uz.tashkent.epam.cources.model.Department;
import uz.tashkent.epam.cources.model.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-spring-config.xml", "classpath:test-dao.xml"})
public class EmployeeDaoJdbcImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void getAllEmployeesTest() {
        List<EmployeeDTO> employees = employeeDao.getAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    void getAllEmployeesByDepartmentId() {
        List<EmployeeDTO> _employees = employeeDao.getAllEmployees();
        assertNotNull(_employees);
        assertTrue(_employees.size() > 0);

        List<EmployeeDTO> employees =
                employeeDao.getAllEmployeesByDepartmentId(_employees.get(0).getDepartmentId());
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
        assertTrue(employees.get(0).getDepartmentId() == _employees.get(0).getDepartmentId());
    }

}
