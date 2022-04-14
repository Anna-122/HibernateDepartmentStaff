package edu.goncharova.validator;

import edu.goncharova.entities.Employee;
import edu.goncharova.services.EmployeeService;
import net.sf.oval.constraint.CheckWithCheck;

public class UniqueEmployeeEmail implements CheckWithCheck.SimpleCheck {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Employee employee = (Employee) validatedObject;
        String employeeEmail = employee.getEmployeeEmail();
        Employee employeeEmailFromDb = employeeService.getByEmail(employeeEmail);
        return employeeEmailFromDb == null || employeeEmailFromDb.getId().equals(employee.getId());
    }
}
