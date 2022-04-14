package edu.goncharova.validator;

import edu.goncharova.entities.Department;
import edu.goncharova.services.DepartmentService;
import net.sf.oval.constraint.CheckWithCheck;

public class UniqueDepartmentName implements CheckWithCheck.SimpleCheck {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Department department = (Department) validatedObject;
        String departmentName = department.getDepartmentName();
        Department name = departmentService.getByDepartmentName(departmentName);
        return name == null || name.getId().equals(department.getId());
    }
}