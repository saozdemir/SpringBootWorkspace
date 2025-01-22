package com.sao.controller.impl;

import com.sao.controller.ITodoController;
import com.sao.dto.TodoDto;
import com.sao.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/rest/api/todo-app")
public class TodoControllerImpl implements ITodoController {

    @Autowired
    private ITodoService todoService;

    @GetMapping(path = "/list")
    @Override
    public List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping(path = "/add")
    @Override
    public TodoDto addTodo(@RequestBody TodoDto addTodo) {
        return todoService.addTodo(addTodo);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public boolean deleteTodo(@PathVariable(name = "id") Integer id) {
        return todoService.deleteTodo(id);
    }
}
