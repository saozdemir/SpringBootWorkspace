package com.sao.controller;

import com.sao.dto.TodoDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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

    TodoDto updateTodo(Integer id, TodoDto newTodo);

    SseEmitter streamTodos();
}
