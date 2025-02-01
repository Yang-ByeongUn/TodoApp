package com.example.demo.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping("/todo/create")
    public ResponseEntity<Object> create(@RequestBody TodoDto todoDto) {
        if(todoDto.getTitle() == null || todoDto.getTitle().isEmpty() || todoDto.getContent() == null || todoDto.getContent().isEmpty()){
            //return new ResponseEntity<>(new ErrorResponse("형식에 맞춰 작성해주세요."), HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("형식에 맞춰 작성해주세요."));
        }
        int savedTodo = service.save(todoDto.getTitle(), todoDto.getContent());
        return new ResponseEntity<>(new TodoDto(savedTodo, todoDto.getTitle(), todoDto.getContent()), HttpStatus.OK);
    }

    @GetMapping("/todo/load/list")
    public ResponseEntity<List<TodoDto>> loadList() {
        List<TodoDto> todos = service.todos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @GetMapping("/todo/load/{id}")
    public ResponseEntity<String> details(@PathVariable int id) {
        if(service.findTodo(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("존재하지 않는 아이디입니다."));
            //return new ResponseEntity<>(new ErrorResponse("존재하지 않는 항목 아이디입니다."), HttpStatus.BAD_REQUEST);
        }
        TodoEntity todo = service.findTodo(id);
        System.out.println("todo = " + todo);
        TodoDto todoDto = new TodoDto(id, todo.getTitle(), todo.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(todoDto.toString());
        //return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PutMapping("/todo/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody TodoDto todoDto) {
        if(todoDto.getTitle() == null || todoDto.getTitle().isEmpty() || todoDto.getContent() == null || todoDto.getContent().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("형식에 맞춰 작성해주세요."));
        }else if(service.findTodo(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("존재하지 않는 아이디입니다."));
        }
        TodoEntity todoEntity = service.updateTodo(id, todoDto.getTitle(), todoDto.getContent());
        TodoDto updatedTodo = new TodoDto(null, todoEntity.getTitle(), todoEntity.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        if(service.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("존재하지 않는 아이디입니다."));
        }
    }

}
