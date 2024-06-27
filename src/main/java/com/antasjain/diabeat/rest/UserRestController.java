package com.antasjain.diabeat.rest;

import com.antasjain.diabeat.config.JwtUtils;
import com.antasjain.diabeat.entity.LogoutRequest;
import com.antasjain.diabeat.entity.Role;
import com.antasjain.diabeat.entity.User;
import com.antasjain.diabeat.exceptions.ResourceNotFoundException;
import com.antasjain.diabeat.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user-api")
public class UserRestController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserRestController(UserService userService, BCryptPasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/user")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResourceNotFoundException("Username already taken: " + user.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role(user.getUsername(), "ROLE_USER");
        user.add(role);
        userService.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/admin")
    public User addUserAdmin(@RequestBody User user) {
        if(userService.existsByUsername(user.getUsername()))
        {
            throw new ResourceNotFoundException("Username already taken " + user.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role(user.getUsername(),"ROLE_USER");
        Role role1 = new Role(user.getUsername(),"ROLE_ADMIN");
        user.add(role);
        user.add(role1);
        return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Optional<User> userOptional = userService.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            String password = user.getPassword();
            User temp = userOptional.get();
            if (passwordEncoder.matches(password, temp.getPassword())) {
                String token = jwtUtils.generateToken(user.getUsername());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("username", temp.getUsername());
                response.put("roles", temp.getRoles()); // Assuming getRoles() returns List<String>
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Incorrect password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

}

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable String username) {
        if (!userService.existsByUsername(username)) {
            throw new ResourceNotFoundException("User not found with username " + username);
        }
        userService.deleteByUsername(username);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest logoutRequest, HttpServletRequest request, HttpServletResponse response) {
        String token = logoutRequest.getToken();
        if (token != null && !token.isEmpty()) {
            if (jwtUtils.validateToken(token)) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                jwtUtils.blacklistToken(token);
                return ResponseEntity.ok("Logout successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is required");
        }
    }


}
