package dev.yemi.britebatch.repository;

import dev.yemi.britebatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
