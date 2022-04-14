package edu.goncharova.entities;

import edu.goncharova.validator.UniqueDepartmentName;
import lombok.*;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter@Setter
@Entity
@Table(name = "departments", schema = "department_staff_hibernate")
public class Department extends Base {

    @Column(name = "department_name")
    @MinLength(value = 4, message = "Department name need minimum 4")
    @MaxLength(value = 20, message = "Department name need maximum 20 letters")
    @NotBlank(message = "Department name can't be empty")
    @CheckWith(value = UniqueDepartmentName.class, message = "Department with this name is exist")
    private String departmentName;

    @OneToMany
    private List<Employee> employees;
}
