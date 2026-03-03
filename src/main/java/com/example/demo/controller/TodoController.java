package com.example.demo.controller;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import com.example.demo.service.TodoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Todo> todos = todoService.findTodos(user);
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping
    public String create(@RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Todo todo = new Todo(content, user);
        todoService.create(todo);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/complete")
    public String complete(@PathVariable Long id, @RequestParam boolean completed) {
        todoService.updateStatus(id, completed);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}
