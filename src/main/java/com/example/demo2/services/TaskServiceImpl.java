package com.example.demo2.services;

import com.example.demo2.domain.Task;
import com.example.demo2.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        Task returnedTask = task;
        if(task.getTask()!="")
            returnedTask = taskRepository.save(task);
        return returnedTask;
    }

    @Override
    public void updateTask(List<Task> tasks) {
        String taskToReplace = tasks.get(0).getTask();
        long idToUpdate = 0;

        //find id to update
        for(Task t : taskRepository.findAll()){
            if(t.getTask().equals(taskToReplace)){
                idToUpdate=t.getId();
                break;
            }
        }

        //Update new task
        tasks.get(1).setId(idToUpdate);
        taskRepository.save(tasks.get(1));
    }

    @Override
    public void deleteTask(Task task) {
        for(Task t : taskRepository.findAll()){
            if(t.getTask().equals(task.getTask())){
                taskRepository.delete(t);
                break;
            }
        }
    }
}
