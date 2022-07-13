package com.jwttest.jwttest.security.service;

import com.jwttest.jwttest.security.entity.Role;
import com.jwttest.jwttest.security.enums.RoleName;
import com.jwttest.jwttest.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
    public void save(Role role) { roleRepository.save(role); }
}
