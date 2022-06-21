package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uz.tashkent.epam.cources.model.Department;

import java.util.List;

class DepartmentDaoJdbcImplTest {

    DepartmentDaoJdbcImpl departmentDaoJdbc = new DepartmentDaoJdbcImpl();

    @Test
    void getAllDepartments() {
        List<Department> departments = departmentDaoJdbc.getAllDepartments();
        Assertions.assertNotNull(departments);
        //Assertions.assertTrue(departments.size());
    }
}