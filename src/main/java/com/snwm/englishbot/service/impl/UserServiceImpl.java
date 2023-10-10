package com.snwm.englishbot.service.impl;

import com.snwm.englishbot.entity.enums.UserType;

import java.util.List;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import com.snwm.englishbot.entity.User;
import com.snwm.englishbot.repository.UserRepository;
import com.snwm.englishbot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createNewUser(Message message) {
        User user = User.builder()
                .chatId(message.getChatId())
                .username(message.getFrom().getUserName())
                .firstName(message.getFrom().getFirstName())
                .lastName(message.getFrom().getLastName())
                .userType(UserType.USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public User getUserByChatId(Long id) {
        return userRepository.findUserByChatId(id);
    }

    @Override
    public void deleteUserWordsByChatId(Long id) {
        userRepository.deleteAllWordsByUserId(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
