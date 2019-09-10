package com.example.demo2.services;

import com.example.demo2.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task saveTask(Task task);
    Boolean updateTask(List<Task> tasks);
    Boolean deleteTask(Task task);
}
