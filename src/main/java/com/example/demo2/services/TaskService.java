package com.example.demo2.services;

import com.example.demo2.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task saveTask(Task customer);

    void updateTask(List<Task> tasks);

    void deleteTask(Task task);
}
