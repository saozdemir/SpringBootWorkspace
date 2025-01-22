package com.sao.controller;

import com.sao.dto.TodoDto;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
public interface ITodoController {

    List<TodoDto> getAllTodos();

    TodoDto addTodo(TodoDto addTodo);

    boolean deleteTodo(Integer id);
}
