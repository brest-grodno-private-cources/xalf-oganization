package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.model.Department;
import uz.tashkent.epam.cources.model.Employee;

import java.util.List;

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
        List<Employee> employees = employeeDao.getAllEmployees();
        Assertions.assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

}
