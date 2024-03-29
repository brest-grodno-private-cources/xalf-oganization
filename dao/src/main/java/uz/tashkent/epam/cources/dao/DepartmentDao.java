package uz.tashkent.epam.cources.dao;

import uz.tashkent.epam.cources.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> getAllDepartments();

    Department addDepartment(String departmentName, String departmentDescription);

    int updateDepartment(Department department);

    int deleteDepartment(int departmentId);

}
