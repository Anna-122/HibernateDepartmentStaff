package edu.goncharova.controller.employee;

import edu.goncharova.controller.Command;
import edu.goncharova.entities.Department;
import edu.goncharova.entities.Employee;
import edu.goncharova.exceptions.ValidateException;
import edu.goncharova.services.DepartmentService;
import edu.goncharova.services.EmployeeService;
import edu.goncharova.utils.DateUtils;
import edu.goncharova.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveEmployeeController implements Command {

    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ValidateException, ServletException, IOException {
        Integer departmentId = NumberUtils.parseNumber(request.getParameter("departments"));
        Integer id = NumberUtils.parseNumber(request.getParameter("id"));
        Department department = new Department();
        department.setId(departmentId);

        Employee employee = new Employee();
        employee.setEmployeeName(request.getParameter("employeeName"));
        employee.setEmployeeSurname(request.getParameter("employeeSurname"));
        employee.setEmployeePhoneNumber(request.getParameter("employeePhoneNumber"));
        employee.setEmployeeEmail(request.getParameter("employeeEmail"));
        employee.setId(id);
        employee.setDepartment(department);
        employee.setEmployeeBirthDate(DateUtils.parseDate(request.getParameter("employeeBirthDate")));

        try {
            employeeService.saveOrUpdate(employee);
            response.sendRedirect("/department/employees?id=" + departmentId);
        } catch (ValidateException e) {
            request.setAttribute("errors", e.getErrors());
            request.setAttribute("employee", employee);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("departments", departmentService.getAllRecords());
            request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeForm.jsp").forward(request, response);
        }
    }
}
