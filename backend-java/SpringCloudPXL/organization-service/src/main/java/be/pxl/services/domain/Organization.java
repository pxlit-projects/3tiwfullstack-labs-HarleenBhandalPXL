package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Organization")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentId")
    private List<Department> departments;
}
