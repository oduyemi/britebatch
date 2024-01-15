package dev.yemi.britebatch.repository;

import dev.yemi.britebatch.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // You can add custom queries here if needed
}
