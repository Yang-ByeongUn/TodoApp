package com.example.demo.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {
    private final TodoRepositoryImpl repository;

    public TodoService(TodoRepositoryImpl repository) {
        this.repository = repository;
    }

    public Integer save(String title, String content){
        int savedId = repository.join(title, content);
        System.out.println("todo 등록 완료");
        return savedId;
    }
    public TodoEntity findTodo(int id){
        return repository.findById(id);
    }
    public List<TodoDto> todos(){
        Map<Integer, TodoEntity> todos = repository.findAll();
        List<TodoDto> list = new ArrayList<>();
        for (TodoEntity value : todos.values()) {
            list.add(new TodoDto(value.getId(), value.getTitle(), null));
        }
        return list;
    }
    public TodoEntity updateTodo(int id, String title, String content){ //예외 사항 체크
        TodoEntity update = repository.update(id, title, content);
        System.out.println("업데이트 완료되었습니다.");
        return update;
    }
    public boolean delete(int id){
        if(repository.findById(id) != null){
            repository.delete(id);
            return true;
        }
        return false;
    }
}
