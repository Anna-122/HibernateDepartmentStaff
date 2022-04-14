package edu.goncharova.controller.deparment;

import edu.goncharova.controller.Command;
import edu.goncharova.services.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ListDepartmentController implements Command {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("departments", departmentService.getAllRecords());
        request.getRequestDispatcher("/WEB-INF/jsp/department/departmentList.jsp").forward(request, response);
    }
}
