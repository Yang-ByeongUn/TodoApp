package com.example.demo.todo;

import java.util.Map;

public interface TodoRepository {
    Integer join(String title, String content);
    TodoEntity findById(int id);
    Map<Integer, TodoEntity> findAll();
    TodoEntity update(int id, String title, String content);
    void delete(int id);
}
