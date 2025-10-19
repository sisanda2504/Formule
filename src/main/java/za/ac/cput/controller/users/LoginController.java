package za.ac.cput.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.domain.users.Manager;
import za.ac.cput.dto.users.LoginRequest;
import za.ac.cput.dto.users.LoginResponse;
import za.ac.cput.security.AppUserDetails;
import za.ac.cput.security.AppUserDetailsService;
import za.ac.cput.security.jwt.JwtUtil;
import za.ac.cput.service.users.IAdminService;
import za.ac.cput.service.users.ICustomerService;
import za.ac.cput.service.users.IManagerService;

@RestController
@RequestMapping("/formule/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final ICustomerService customerService;
    private final IAdminService adminService;
    private final IManagerService managerService;

    @Autowired
    public LoginController(
            AuthenticationManager authenticationManager,
            AppUserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            ICustomerService customerService,
            IAdminService adminService,
            IManagerService managerService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.customerService = customerService;
        this.adminService = adminService;
        this.managerService = managerService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            AppUserDetails appUser = (AppUserDetails) userDetails;

            String token = jwtUtil.generateToken(userDetails);

            String role = appUser.getAuthorities().iterator().next().getAuthority();
            String firstName = null;
            String lastName = null;
            String phoneNumber = null;

            switch (role) {
                case "ROLE_CUSTOMER":
                    Customer customer = customerService.read(appUser.getId());
                    if (customer != null) {
                        firstName = customer.getFirstName();
                        lastName = customer.getLastName();
                        phoneNumber = customer.getPhoneNumber();
                    }
                    break;

                case "ROLE_ADMIN":
                    Admin admin = adminService.read(appUser.getId());
                    if (admin != null) {
                        firstName = admin.getFirstName();
                        lastName = admin.getLastName();
                        phoneNumber = admin.getPhoneNumber();
                    }
                    break;

                case "ROLE_MANAGER":
                    Manager manager = managerService.read(appUser.getId());
                    if (manager != null) {
                        firstName = manager.getFirstName();
                        lastName = manager.getLastName();
                        phoneNumber = manager.getPhoneNumber();
                    }
                    break;
            }

            LoginResponse response = new LoginResponse(
                    appUser.getId(),
                    firstName,
                    lastName,
                    phoneNumber,
                    appUser.getUsername(),
                    role
            );
            response.setToken(token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}