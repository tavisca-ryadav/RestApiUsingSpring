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
    public Boolean updateTask(List<Task> tasks) {
        String taskToReplace = tasks.get(0).getTask();
        long idToUpdate = 0;
        Boolean taskUpdated = false;

        //find id to update
        for(Task t : taskRepository.findAll()){
            if(t.getTask().equals(taskToReplace)){
                taskUpdated = true;
                idToUpdate=t.getId();
                break;
            }
        }

        //Update new task
        tasks.get(1).setId(idToUpdate);
        taskRepository.save(tasks.get(1));
        return taskUpdated;
    }

    @Override
    public Boolean deleteTask(Task task) {
        Boolean taskFound=false;
        for(Task t : taskRepository.findAll()){
            if(t.getTask().equals(task.getTask())){
                taskFound=true;
                taskRepository.delete(t);
                break;
            }
        }
        return taskFound;
    }
}
