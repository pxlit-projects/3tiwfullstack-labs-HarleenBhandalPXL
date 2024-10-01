package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class OrganizationTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrganizationRepository organizationRepository;

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
        organizationRepository.deleteAll();
    }

    @Test
    public void testGetOrganizationById() throws Exception {
        Organization organization = Organization.builder()
                .name("Organization")
                .address("Address")
                .build();

        organizationRepository.save(organization);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + organization.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        Organization returnedOrganization = objectMapper.readValue(response, Organization.class);

        assertEquals("Organization", returnedOrganization.getName());
    }

    @Test
    public void testGetOrganizationByIdWithDepartments() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-departments"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetOrganizationByIdWithDepartmentsAndEmployees() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-departments-and-employees"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetOrganizationByIdWithEmployees() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-employees"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
