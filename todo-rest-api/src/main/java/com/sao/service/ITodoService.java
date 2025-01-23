package com.sao.service;

import com.sao.dto.TodoDto;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
public interface ITodoService {
    List<TodoDto> getAllTodos();
    TodoDto addTodo (TodoDto addTodo);
    boolean deleteTodo (Integer id);
    TodoDto updateTodo (Integer id, TodoDto newTodo);
}
