package edu.goncharova.entities;

import edu.goncharova.validator.UniqueEmployeeEmail;
import edu.goncharova.validator.UniquePhoneNumber;
import lombok.Getter;
import lombok.Setter;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;

@Getter@Setter
@Entity
@Table(name = "employees", schema = "department_staff_hibernate")
public class Employee extends Base {

    @Column(name = "employee_name")
    @NotBlank(message = "Name field is null or empty")
    @MaxLength(value = 20, message = " Name can have maximum 20 letters")
    @MatchPattern(pattern = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message = "Name is not valid")
    private String employeeName;

    @Column(name = "employee_surname")
    @NotBlank(message = "Surname field is null or empty")
    @MaxLength(value = 20, message = " Surname can have maximum 20 letters")
    @MatchPattern(pattern = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message = "Surname is not valid")
    private String employeeSurname;

    @Column(name = "employee_date_birthday")
    @NotNull(message = "Can't be empty")
    private Date employeeBirthDate;

    @Column(name = "employee_phone_number")
    @NotBlank(message = "Phone Number field is null or empty")
    @CheckWith(value = UniquePhoneNumber.class, message = "phone number is exist in database")
    @MatchPattern(pattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Phone number is not valid")
    private String employeePhoneNumber;

    @Column(name = "employee_email", unique = true)
    @NotBlank(message = "Email field is null or empty")
    @CheckWith(value = UniqueEmployeeEmail.class, message = "Field is exist in database")
    @Email(message = "Email is not valid.")
    private String employeeEmail;

    @ManyToOne
    private Department department;
}
