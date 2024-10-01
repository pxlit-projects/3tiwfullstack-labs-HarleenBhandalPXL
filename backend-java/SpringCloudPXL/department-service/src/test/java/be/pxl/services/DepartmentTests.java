package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class DepartmentTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

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
        departmentRepository.deleteAll();
    }

    @Test
    public void testGetDepartments() throws Exception {
        Department department = Department.builder()
                .name("Department 1")
                .organizationId(1L)
                .build();

        departmentRepository.save(department);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/department")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        List<Department> departments = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Department.class));

        assertEquals(1, departments.size());
    }

    @Test
    public void testAddDepartment() throws Exception {
        DepartmentRequest department = DepartmentRequest.builder()
                .name("Department 1")
                .organizationId(1L)
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    public void getDepartmentById() throws Exception {
        Department department = Department.builder()
                .name("Department 1")
                .organizationId(1L)
                .build();

        departmentRepository.save(department);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/" + department.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        Department returnedDepartment = objectMapper.readValue(response, Department.class);

        assertEquals("Department 1", returnedDepartment.getName());
    }

    @Test
    public void getDepartmentByOrganizationId() throws Exception {
        Department department1 = Department.builder()
                .name("Department 1")
                .organizationId(1L)
                .build();

        Department department2 = Department.builder()
                .name("Department 1")
                .organizationId(1L)
                .build();

        departmentRepository.save(department1);
        departmentRepository.save(department2);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        List<Department> departments = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Department.class));

        assertEquals(2, departments.size());
    }

    @Test
    public void getDepartmentsByOrganizationIdWithEmployees() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1/with-employees"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
