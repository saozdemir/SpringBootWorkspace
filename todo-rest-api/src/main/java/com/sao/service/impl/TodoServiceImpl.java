package com.sao.service.impl;

import com.sao.dto.TodoDto;
import com.sao.entity.Todo;
import com.sao.repository.TodoRepository;
import com.sao.service.ITodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Oca 2025
 * <p>
 * @description:
 */
@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoDto> getAllTodos() {
        List<TodoDto> dtoList = new ArrayList<>();
        List<Todo> dbList = todoRepository.findAll();
        for (Todo todo : dbList){
            TodoDto dto = new TodoDto();
            BeanUtils.copyProperties(todo, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public TodoDto addTodo(TodoDto addTodo) {
        Todo todo = new Todo();
        TodoDto responseTodo = new TodoDto();
        BeanUtils.copyProperties(addTodo, todo);
        todo.setCreateDate(new Date());
        todo = todoRepository.save(todo);
        BeanUtils.copyProperties(todo,responseTodo);
        return responseTodo;
    }

    @Override
    public boolean deleteTodo(Integer id) {
        Optional<Todo> optional = todoRepository.findById(id);
        if(optional.isPresent()) {
            todoRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
