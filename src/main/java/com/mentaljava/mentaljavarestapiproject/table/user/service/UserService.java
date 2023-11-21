package com.mentaljava.mentaljavarestapiproject.table.user.service;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User findOne(String userID) {
        return userRepository.findByUserId(userID);
    }

    public void updateNickname(String userId, String nickname ){
        User user = userRepository.findByUserId(userId);
        user.setNickname(nickname);
        userRepository.save(user);
    }
}
