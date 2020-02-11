package com.home.server.persistence.repository;

import com.home.server.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndDeletedFalse(String email);

    List<User> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query("UPDATE User as user set user.deleted = true WHERE user.id=:id")
    void softDelete(@Param("id") Long id);
}
