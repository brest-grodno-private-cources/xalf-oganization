package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.model.Department;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
                departmentDao.addDepartment("HR", "HR Department Descr");
        assertTrue(newDepartment.getDepartmentName().equals("HR"));
        assertTrue(newDepartment.getDepartmentDescription().equals("HR Department Descr"));
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

    @Test
    void updateDepartment() {
        List<Department> departmentsBefore = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsBefore);
        assertTrue(departmentsBefore.size() > 0);
        Department toUpdateDepartment = departmentsBefore.get(0);

        String oldName = toUpdateDepartment.getDepartmentName();
        String oldDescr = toUpdateDepartment.getDepartmentDescription();

        toUpdateDepartment.setDepartmentName(oldName + "_UPDATED");
        toUpdateDepartment.setDepartmentDescription(oldDescr + "_UPDATED");

        int updateResult = departmentDao.updateDepartment(toUpdateDepartment);
        assertTrue(updateResult == 1);

        List<Department> departmentsAfter = departmentDao.getAllDepartments();
        for (Department department : departmentsAfter) {
            if (department.getDepartmentId().equals(toUpdateDepartment.getDepartmentId())) {
                assertEquals(oldName + "_UPDATED", department.getDepartmentName());
                assertEquals(oldDescr + "_UPDATED", department.getDepartmentDescription());
                break;
            }
        }
    }

    @Test
    void updateSetNotUniqueDepartment() {
        List<Department> departmentsBefore = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsBefore);
        assertTrue(departmentsBefore.size() >= 2);

        Department toUpdateDepartment = departmentsBefore.get(0);
        Department secondDepartment = departmentsBefore.get(1);

        toUpdateDepartment.setDepartmentName(secondDepartment.getDepartmentName());
        toUpdateDepartment.setDepartmentDescription(secondDepartment.getDepartmentDescription());

        assertThrows(IllegalArgumentException.class ,() -> {
            departmentDao.updateDepartment(toUpdateDepartment);
        });
    }


    @Test
    void deleteDepartment() {

        List<Department> departmentsBefore = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsBefore);
        assertTrue(departmentsBefore.size() > 0);
        Department toDeleteDepartment = departmentsBefore.get(0);

        int updateResult = departmentDao.deleteDepartment(toDeleteDepartment.getDepartmentId());
        assertTrue(updateResult == 1);

        List<Department> departmentsAfter = departmentDao.getAllDepartments();
        assertTrue((departmentsBefore.size() - 1) == departmentsAfter.size());
    }

}