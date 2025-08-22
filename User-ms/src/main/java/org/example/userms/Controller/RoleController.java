package org.example.userms.Controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.userms.Dto.request.RolesRequestDto;
import org.example.userms.Dto.response.RolesResponseDto;
import org.example.userms.Model.Roles;
import org.example.userms.Service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.resolve;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RolesService rolesService;

//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new Roles")
    @PostMapping("/create")
    public ResponseEntity <Long> createRole(@RequestBody RolesRequestDto rolesRequestDto) {
        return new ResponseEntity<>(rolesService.createRole(rolesRequestDto) ,OK);

    }

    @Operation(summary = "Find Roles by Id")
    @GetMapping("/findById/{id}")
    public RolesResponseDto findByDentistId(@PathVariable Long id, ServletRequest servletRequest) {
        return rolesService.findById(id);
    }

    @Operation(summary = "Getting all Roles")
    @GetMapping("/getAll")
    public List<RolesResponseDto> getAllRoles() {
        return rolesService.getAll();
    }

    @Operation(summary = "Deleting Role")
    @DeleteMapping("/deleteRole")
    public void deleteRole(@RequestBody RolesRequestDto rolesRequestDto) {
        rolesService.deleteRole(rolesRequestDto);
    }


}
