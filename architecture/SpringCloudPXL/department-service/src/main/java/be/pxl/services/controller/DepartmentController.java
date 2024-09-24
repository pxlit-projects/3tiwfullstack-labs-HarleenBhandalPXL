package be.pxl.services.controller;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest department) {
        departmentService.addDepartment(department);
    }

    @GetMapping("/{id}")
    public ResponseEntity findDepartmentById(@PathVariable Long id) {
        return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity findByOrganizationId(@PathVariable Long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentByOrganization(organizationId), HttpStatus.OK);
    }

    @GetMapping("organization/{organizationId}/with-employees")
    public ResponseEntity findByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentByOrganizationWithEmployees(organizationId), HttpStatus.OK);
    }
}
