package be.pxl.services.services;

import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.NotificationRequest;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee e) {
        return EmployeeResponse.builder()
                .age(e.getAge())
                .name(e.getName())
                .position(e.getPosition())
                .departmentId(e.getDepartmentId())
                .organizationId(e.getOrganizationId())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .age(employeeRequest.getAge())
                .name(employeeRequest.getName())
                .position(employeeRequest.getPosition())
                .departmentId(employeeRequest.getDepartmentId())
                .organizationId(employeeRequest.getOrganizationId())
                .build();

        employeeRepository.save(employee);
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message("Employee added")
                .from("HarryFerrari")
                .subject("EmployeeService")
                .to("Joske")
                .build();

        notificationClient.sendNotification(notificationRequest);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        return mapToEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public List<EmployeeResponse> getEmployeesByOrganizationId(Long organizationId) {
        List<Employee> employees = employeeRepository.findByOrganizationId(organizationId);

        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }
}
