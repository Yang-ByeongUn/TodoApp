package com.example.demo.todo;


public class TodoDto {
    Integer id;
    String title;
    String content;

    public TodoDto() {
    }

    public TodoDto(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TodoDto{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }
}
