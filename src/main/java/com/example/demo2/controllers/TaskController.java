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
    public ResponseEntity saveTask(@RequestBody Task task){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        if(task.getTask()!="") {
            taskService.saveTask(task);
            responseEntity = new ResponseEntity(HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @CrossOrigin
    @DeleteMapping("/deletes")
    public ResponseEntity delteTask(@RequestBody Task task){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        if(task.getTask()!="") {
            taskService.deleteTask(task);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }
        return responseEntity;
    }

    @CrossOrigin
    @PutMapping("/puts")
    public ResponseEntity updateTask(@RequestBody List<Task> tasks){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NOT_MODIFIED);
        if(tasks.get(0).getTask()!="" && tasks.get(1).getTask()!="") {
            taskService.updateTask(tasks);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }
        return responseEntity;
    }
}