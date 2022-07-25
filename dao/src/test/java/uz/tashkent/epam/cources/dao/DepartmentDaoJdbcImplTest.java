package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.model.Department;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-spring-config.xml", "classpath:test-dao.xml"})
class DepartmentDaoJdbcImplTest {

    public static final String NEW_DEPARTMENT = "NEWDepartment";

    @Autowired
    DepartmentDao departmentDao;

    @Test
    void getAllDepartmentsTest() {
        List<Department> departments = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    void addDepartmentTest() {
        List<Department> departmentsBefore = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsBefore);
        assertTrue(departmentsBefore.size() > 0);
        Integer departmentsSizeBefore = departmentsBefore.size();
        Department newDepartment =
                departmentDao.addDepartment(NEW_DEPARTMENT, "NEWDepartment Descr");
        assertTrue(newDepartment.getDepartmentName().equals(NEW_DEPARTMENT));
        assertTrue(newDepartment.getDepartmentDescription().equals("NEWDepartment Descr"));
        List<Department> departmentsAfter = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsAfter);
        assertTrue(departmentsSizeBefore < departmentsAfter.size());
    }

    @Test
    void addNotUniqueDepartmentTest() {
        departmentDao.addDepartment(NEW_DEPARTMENT, "NEWDepartment Descr");
        Exception exception = assertThrows(IllegalArgumentException.class ,() -> {
            departmentDao.addDepartment(NEW_DEPARTMENT, "");
        });
        String expectedMessage = "Department with name '" + NEW_DEPARTMENT + "' already exists in DB.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}