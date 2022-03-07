package com.example.challengeAlkemy.services;

import com.example.challengeAlkemy.exceptions.EmailAlreadyRegistered;
import com.example.challengeAlkemy.exceptions.UsernameAlreadyExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.User;
import com.example.challengeAlkemy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User register(User user) throws EmailAlreadyRegistered, UsernameAlreadyExistsException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyRegistered();
        }else if (userRepository.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExistsException();
        }else{
            return userRepository.save(user);

        }
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
