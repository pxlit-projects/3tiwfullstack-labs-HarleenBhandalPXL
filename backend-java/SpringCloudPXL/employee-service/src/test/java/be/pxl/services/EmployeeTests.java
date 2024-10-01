package be.pxl.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class EmployeeTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeEach
    public void clearDatabase() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeRequest employee = EmployeeRequest.builder()
                .age(24)
                .name("Joske")
                .position("student")
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeString))
                .andExpect(status().isCreated());

        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void testGetEmployees() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Joske")
                .position("student")
                .build();

        employeeRepository.save(employee);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        List<Employee> employees = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        assertEquals(1, employees.size());
    }

    @Test
    public void getEmployeeById() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Joske")
                .position("student")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/" + savedEmployee.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        Employee returnedEmployee = objectMapper.readValue(response, Employee.class);

//        assertEquals(savedEmployee, returnedEmployee);
        assertEquals("Joske", returnedEmployee.getName());
    }

    @Test
    public void testFindEmloyeesByDepartmentId() throws Exception {
        Employee employee1 = Employee.builder()
                .age(24)
                .name("Joske")
                .position("student")
                .departmentId(5L)
                .build();

        Employee employee2 = Employee.builder()
                .age(24)
                .name("Merijn")
                .position("student")
                .departmentId(1L)
                .build();

        Employee employee3 = Employee.builder()
                .age(24)
                .name("Ferrari")
                .position("student")
                .departmentId(1L)
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/1"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        List<Employee> employees = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        assertEquals(2, employees.size());
    }

    @Test
    public void testFindEmloyeesByOrganizationId() throws Exception {
        Employee employee1 = Employee.builder()
                .age(24)
                .name("Joske")
                .position("student")
                .organizationId(5L)
                .build();

        Employee employee2 = Employee.builder()
                .age(24)
                .name("Merijn")
                .position("student")
                .organizationId(1L)
                .build();

        Employee employee3 = Employee.builder()
                .age(24)
                .name("Ferrari")
                .position("student")
                .organizationId(1L)
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/organization/1"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        List<Employee> employees = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        assertEquals(2, employees.size());
    }
}
