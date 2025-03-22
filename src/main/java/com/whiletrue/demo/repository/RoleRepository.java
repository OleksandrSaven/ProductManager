package com.whiletrue.demo.repository;

import com.whiletrue.demo.model.Role;
import com.whiletrue.demo.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName name);
}
