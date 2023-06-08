package com.douk.PMS.repo;


import com.douk.PMS.entity.Role;
import com.douk.PMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String pasword);
    Optional<User> findByEmail(String email);
    List<User> findAllByUserRole(Role userRole);
    boolean existsByEmail(String email);
}
