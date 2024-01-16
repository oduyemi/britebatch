import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        
        return ResponseEntity.ok("User logged in successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUserProfile) {
        if (userRepository.existsById(userId)) {
            updatedUserProfile.setId(userId);
            userRepository.save(updatedUserProfile);
            return ResponseEntity.ok("Profile updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/updateUserRole/{userId}/{roleId}")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            user.setUserRole(optionalRole.get());
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
