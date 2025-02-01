package com.example.demo.todo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    Map<Integer, TodoEntity> map = new HashMap<>();
    int nextId = 1;
    @Override
    public Integer join(String title, String content) {
        TodoEntity todoEntity = new TodoEntity(nextId, title, content);
        map.put(todoEntity.getId(), todoEntity);
        nextId++;
        return todoEntity.getId();
    }
    @Override
    public TodoEntity findById(int id){
        return map.get(id);
    }
    @Override
    public Map<Integer, TodoEntity> findAll(){
        return new HashMap<>(map);
    }
    @Override
    public TodoEntity update(int id, String title, String content){
        TodoEntity todoEntity = map.get(id);
        todoEntity.setTitle(title);
        todoEntity.setContent(content);
        map.replace(id, todoEntity);
        return todoEntity;
        //간단한게 둘 다 넣게 만들었습니다.
    }
    @Override
    public void delete(int id){
        map.remove(id);
    }
}
