package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Long create(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    public List<Todo> findTodos(User user) {
        return todoRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Todo> findTodosByStatus(User user, boolean completed) {
        return todoRepository.findByUserAndCompletedOrderByCreatedAtDesc(user, completed);
    }

    public void updateContent(Long todoId, String content) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."));
        todo.setContent(content);
    }

    public void updateStatus(Long todoId, boolean completed) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."));
        todo.setCompleted(completed);
    }

    public void delete(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
