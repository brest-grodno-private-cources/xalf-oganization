package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.model.Department;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
class DepartmentDaoJdbcImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    void getAllDepartmentsTest() {
        List<Department> departments = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);
    }

    @Test
    void addDepartmentTest() {
        List<Department> departmentsBefore = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsBefore);
        Assertions.assertTrue(departmentsBefore.size() > 0);
        Integer departmentsSizeBefore = departmentsBefore.size();
        Department newDepartment =
                departmentDao.addDepartment("NEWDepartment", "NEWDepartment Descr");
        Assertions.assertTrue(newDepartment.getDepartmentName().equals("NEWDepartment"));
        Assertions.assertTrue(newDepartment.getDepartmentDescription().equals("NEWDepartment Descr"));
        List<Department> departmentsAfter = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departmentsAfter);
        Assertions.assertTrue(departmentsSizeBefore < departmentsAfter.size());
    }
}