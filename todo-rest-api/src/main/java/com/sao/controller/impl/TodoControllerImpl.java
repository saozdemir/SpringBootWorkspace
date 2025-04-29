package com.sao.controller.impl;

import com.sao.controller.ITodoController;
import com.sao.dto.TodoDto;
import com.sao.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/api/todo-app")
public class TodoControllerImpl implements ITodoController {

    @Autowired
    private ITodoService todoService;

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping(path = "/list")
    @Override
    public List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping(path = "/add")
    @Override
    public TodoDto addTodo(@RequestBody TodoDto addTodo) {
        TodoDto newTodo = todoService.addTodo(addTodo);
        notifyClients();
        return newTodo;
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public boolean deleteTodo(@PathVariable(name = "id") Integer id) {
        boolean isDeleted = todoService.deleteTodo(id);
        if(isDeleted) {
            notifyClients();
        }
        return isDeleted;
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public TodoDto updateTodo(@PathVariable(name = "id") Integer id, @RequestBody TodoDto newTodo) {
        TodoDto updatedTodo = todoService.updateTodo(id, newTodo);
        notifyClients();
        return updatedTodo;
    }

    @GetMapping(path = "/event")
    @Override
    public SseEmitter streamTodos() {
        SseEmitter emitter = new SseEmitter(60_000L); // 60 saniye timeout
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            emitters.remove(emitter);
        });

        // İlk bağlantıda tüm todoları gönder
        try {
            emitter.send(SseEmitter.event()
                    .name("todos")
                    .data(todoService.getAllTodos()));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    private void notifyClients() {
        List<TodoDto> todos = todoService.getAllTodos();
        List<SseEmitter> deadEmitters = new ArrayList<>();

        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("todos")
                        .data(todos));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }
}
