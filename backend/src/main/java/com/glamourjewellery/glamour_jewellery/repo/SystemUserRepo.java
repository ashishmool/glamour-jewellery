package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SystemUserRepo extends JpaRepository<SystemUser, Long> {

    Optional<SystemUser> findByEmail(String email);

    @Query("SELECT new map(u.userId as userId, u.username as username, u.email as email, u.role as role) FROM SystemUser u")
    List<Map<String, Object>> findAllWithoutPassword();


}
