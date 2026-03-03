package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Long join(User user) {
    validateDuplicateUser(user);
    userRepository.save(user);
    return user.getId();
  }

  private void validateDuplicateUser(User user) {
    userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    });
  }

  public Optional<User> login(String email, String password) {
    return userRepository.findByEmail(email).filter(u -> u.getPassword().equals(password));
  }
}
