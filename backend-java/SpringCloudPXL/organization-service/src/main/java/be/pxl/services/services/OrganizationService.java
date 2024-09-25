package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
//        Organization organization = organizationRepository.findById(id).orElse(null);
//
//        if (organization == null) {
//            return null;
//        }

//        return mapToOrganizationResponse(organization);
        return null;
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .departments(organization.getDepartments())
                .build();
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartments(Long id) {
//        Organization organization = organizationRepository.findOrganizationByIdWithDepartments(id).orElse(null);
//
//        if (organization == null) {
//            return null;
//        }

//        return mapToOrganizationResponse(organization);
        return null;
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
//        Organization organization = organizationRepository.getOrganizationByIdWithDepartmentsAndEmployees(id).orElse(null);

//        if (organization == null) {
//            return null;
//        }

//        return mapToOrganizationResponse(organization);
        return null;
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithEmployees(Long id) {
//        Organization organization = organizationRepository.findOrganizationByIdWithEmployees(id).orElse(null);

//        if (organization == null) {
//            return null;
//        }

//        return mapToOrganizationResponse(organization);
        return null;
    }
}
