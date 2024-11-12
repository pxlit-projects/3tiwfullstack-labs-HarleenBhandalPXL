package be.pxl.services.repository;

import be.pxl.services.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentByOrganizationId(Long organizationId);

    /*@Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.organizationId = :organizationId")
    List<Department> findByOrganizationWithEmployees(Long organizationId);*/
}
