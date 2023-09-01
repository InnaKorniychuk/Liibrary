package com.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.spring.library.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
