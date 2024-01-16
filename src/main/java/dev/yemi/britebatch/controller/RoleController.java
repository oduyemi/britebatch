import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        return optionalRole.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created successfully");
    }

    @PutMapping("/assignRole/{userId}/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            user.setRole(optionalRole.get());
            userRepository.save(user);
            return ResponseEntity.ok("Role assigned to user successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or role not found");
        }
    }

    @PutMapping("/updateUserRole/{userId}/{roleId}")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            user.setRole(optionalRole.get());
            userRepository.save(user);
            return ResponseEntity.ok("User role updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or role not found");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("User account successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
