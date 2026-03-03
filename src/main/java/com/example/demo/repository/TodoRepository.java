package com.example.demo.repository;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserOrderByCreatedAtDesc(User user);
    List<Todo> findByUserAndCompletedOrderByCreatedAtDesc(User user, boolean completed);
}
