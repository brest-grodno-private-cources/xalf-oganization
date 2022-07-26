package uz.tashkent.epam.cources.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import uz.tashkent.epam.cources.model.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private static final String SQL_GET_ALL_DEPARTMENTS =
            "select department_id, department_name from department order by department_name";

    private static final String SQL_GET_DEPARTMENT_BY_NAME =
            "select count(department_id) as cnt from department where lower(department_name) = ?";

    private static final String SQL_ADD_DEPARTMENT =
            "INSERT INTO DEPARTMENT(department_name, department_description) VALUES(?, ?)";

    public static final String DEPARTMENT_ID = "department_id";
    public static final String DEPARTMENT_NAME = "department_name";
    public static final String DEPARTMENT_DESCRIPTION = "department_description";

    //CRUD C-create R-read U-update D-delete

    JdbcTemplate jdbcTemplate;

    public DepartmentDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = jdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department addDepartment(String departmentName, String departmentDescription) {

        if (isDepartmentNameExists(departmentName)) {
            throw new IllegalArgumentException("Department with name '" + departmentName + "' already exists in DB.");
        }

        var preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(SQL_ADD_DEPARTMENT, Types.VARCHAR, Types.VARCHAR) {
            {
                setReturnGeneratedKeys(true);
                setGeneratedKeysColumnNames(DEPARTMENT_ID);
            }
        };

        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory
                        .newPreparedStatementCreator(new Object[]{departmentName, departmentDescription});
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return new Department(keyHolder.getKey().intValue(), departmentName, departmentDescription);
    }

    private boolean isDepartmentNameExists(String departmentName) {

        var preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(SQL_GET_DEPARTMENT_BY_NAME, Types.VARCHAR);
        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory
                        .newPreparedStatementCreator(new Object[]{departmentName.trim().toLowerCase()});

        Integer foundRecordsNumber = jdbcTemplate.query(preparedStatementCreator, rs -> {
            if (rs.next()) {
                return rs.getInt("cnt");
            }
            return 0;
        });

        return foundRecordsNumber > 0;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer departmentId = rs.getInt(DEPARTMENT_ID);
            String departmentName = rs.getString(DEPARTMENT_NAME);
            String departmentDescription = rs.getString(DEPARTMENT_DESCRIPTION);
            return new Department(departmentId, departmentName, departmentDescription);
        }
    }
}
