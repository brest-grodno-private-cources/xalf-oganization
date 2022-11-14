package uz.tashkent.epam.cources.dao;

import uz.tashkent.epam.cources.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesByDepartmentId(Integer departmentId);

    Employee addEmployee(String firstName, String lastName, String email, Integer departmentId);

    int updateEmployee(String firstName, String lastName, String email);

    int deleteEmployee(Integer employeeId);

    void moveEmployeeToDepartment(Integer employeeId, Integer departmentId);

}
