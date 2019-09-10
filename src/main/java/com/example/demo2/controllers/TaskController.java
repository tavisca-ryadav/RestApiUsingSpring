package com.example.demo2.controllers;

import com.example.demo2.domain.Task;
import com.example.demo2.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

    public static final String BASE_URL = "/api/v1/tasks";

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.findAllTasks();
    }

    @CrossOrigin
    @PostMapping("/posts")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        if(!task.getTask().equals(""))
            return new ResponseEntity<>(taskService.saveTask(task),HttpStatus.CREATED);
        return new ResponseEntity<>("Empty task is not allowed",HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @DeleteMapping("/deletes")
    public ResponseEntity<?> delteTask(@RequestBody Task task){
        if(!task.getTask().equals("") && taskService.deleteTask(task))
            return new ResponseEntity<>(task,HttpStatus.OK);
        return new ResponseEntity<>("Task not found",HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @PutMapping("/puts")
    public ResponseEntity<?> updateTask(@RequestBody List<Task> tasks){
        if(!tasks.get(0).getTask().equals("") && !tasks.get(1).getTask().equals("") && taskService.updateTask(tasks))
            return new ResponseEntity<>(tasks.get(1),HttpStatus.OK);
        return new ResponseEntity<>("Task is empty or not found",HttpStatus.NOT_MODIFIED);
    }
}