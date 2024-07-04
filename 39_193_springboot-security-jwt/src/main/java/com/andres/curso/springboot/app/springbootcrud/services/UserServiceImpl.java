package com.andres.curso.springboot.app.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andres.curso.springboot.app.springbootcrud.entities.Role;
import com.andres.curso.springboot.app.springbootcrud.entities.User;
import com.andres.curso.springboot.app.springbootcrud.repositories.RoleRepository;
import com.andres.curso.springboot.app.springbootcrud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<Role>optionalRoleUser = this.roleRepository.findByName("ROLE_USER");
        List<Role>roles = new ArrayList<>();
        optionalRoleUser.ifPresent(r -> roles.add(r));
        if(user.isAdmin()){
            Optional<Role>optionalRoleAdmin = this.roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(r -> roles.add(r));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public boolean existByUsername(String username) {
        return this.userRepository.existByUsername(username);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
}
