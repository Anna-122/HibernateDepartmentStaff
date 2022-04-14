package edu.goncharova.services;

import edu.goncharova.dao.EmployeeDAO;
import edu.goncharova.entities.Employee;
import edu.goncharova.validator.CustomOValValidator;
import edu.goncharova.exceptions.ValidateException;

import java.util.List;

public class EmployeeService implements ServiceFactory<Employee, Integer> {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public void saveOrUpdate(Employee employee) throws ValidateException {
        validator.validate(employee);
        employeeDAO.saveOrUpdate(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    public Employee getById(Integer id) {
        return employeeDAO.find(id);
    }

    @Override
    public List<Employee> getAllRecords() {
        return employeeDAO.findAll();
    }

    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return employeeDAO.findAll(departmentId);
    }

    public Employee getByEmail(String email) {
        return employeeDAO.getByField(email);
    }

    public Employee getByPhoneNumber(String phoneNumber) {
        return employeeDAO.getByNumber(phoneNumber);
    }
}