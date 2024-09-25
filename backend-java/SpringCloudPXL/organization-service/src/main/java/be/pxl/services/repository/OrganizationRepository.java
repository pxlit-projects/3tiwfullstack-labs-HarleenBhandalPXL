package be.pxl.services.repository;

import be.pxl.services.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
//    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.departments WHERE o.id = :id")
//    Optional<Organization> findOrganizationByIdWithDepartments(Long id);
//    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.employees WHERE o.id = :id")
//    Optional<Organization> findOrganizationByIdWithEmployees(Long id);
//    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.departments d LEFT JOIN FETCH d.employees WHERE o.id = :id")
//    Optional<Organization> getOrganizationByIdWithDepartmentsAndEmployees(Long id);
}
