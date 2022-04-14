package edu.goncharova.validator;

import edu.goncharova.entities.Employee;
import edu.goncharova.services.EmployeeService;
import net.sf.oval.constraint.CheckWithCheck;

public class UniquePhoneNumber implements CheckWithCheck.SimpleCheck {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Employee employee = (Employee) validatedObject;
        String employeePhoneNumber = employee.getEmployeePhoneNumber();
        Employee byPhoneNumber = employeeService.getByPhoneNumber(employeePhoneNumber);
        return byPhoneNumber == null
                || byPhoneNumber.getEmployeePhoneNumber().equals(employee.getEmployeePhoneNumber());
    }
}
