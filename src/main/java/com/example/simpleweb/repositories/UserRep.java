package com.example.simpleweb.repositories;

import com.example.simpleweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRep extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String name);
    boolean existsByNameAndPassword(String name, String pass);
    @Query("SELECT u FROM UserEntity u")
    List<UserEntity> findAllUsers();
}
