package be.pxl.services.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;

    private Long organizationId;
    private String name;
    private List<Employee> employees;
    private String position;
}
