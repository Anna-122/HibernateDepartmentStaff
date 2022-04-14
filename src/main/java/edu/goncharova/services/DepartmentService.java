package edu.goncharova.services;

import edu.goncharova.dao.DepartmentDAO;
import edu.goncharova.entities.Department;
import edu.goncharova.exceptions.ValidateException;

import java.util.List;

public class DepartmentService implements ServiceFactory<Department, Integer> {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    public DepartmentService() {
    }

    @Override
    public void saveOrUpdate(Department department) throws ValidateException {
        validator.validate(department);
        departmentDAO.saveOrUpdate(department);
    }

    @Override
    public void delete(Department department) {
        departmentDAO.delete(department);
    }

    @Override
    public Department getById(Integer id) {
        return departmentDAO.find(id);
    }

    @Override
    public List<Department> getAllRecords() {
        return departmentDAO.findAll();
    }

    public Department getByDepartmentName(String name) {
        return departmentDAO.getByField(name);
    }
}


