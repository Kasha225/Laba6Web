package com.example.simpleweb.services;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.example.simpleweb.entity.UserEntity;
import com.example.simpleweb.repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
private UserRep userRep;

    public void saveUser(UserEntity user){
    userRep.save(user);
}

    public UserEntity findByName(String name) {
        return userRep.findByName(name);
    }
    public boolean existsByNameAndPassword(String name, String pass) {
        return userRep.existsByNameAndPassword(name, pass);
    }
    public List<UserEntity> getAllUsers() {
        return userRep.findAllUsers();
    }
}
