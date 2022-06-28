package uz.tashkent.epam.cources.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.tashkent.epam.cources.model.Department;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml"})
class DepartmentDaoJdbcImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getAllDepartments() {
        DepartmentDao departmentDao = new DepartmentDaoJdbcImpl(jdbcTemplate);
        List<Department> departments = departmentDao.getAllDepartments();
        Assertions.assertNotNull(departments);
        //Assertions.assertTrue(departments.size());
    }
}