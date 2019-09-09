package com.example.demo2.bootstrap;

import com.example.demo2.domain.Task;
import com.example.demo2.repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public BootStrapData(TaskRepository customerRepository) {
        this.taskRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Tasks");

        Task t1 = new Task();
        t1.setTask("Eat");
        taskRepository.save(t1);

        Task t2 = new Task();
        t2.setTask("Sleep");
        taskRepository.save(t2);

        Task t3 = new Task();
        t3.setTask("Cook");
        taskRepository.save(t3);

        Task t4 = new Task();
        t4.setTask("Basketball");
        taskRepository.save(t4);

        Task t5 = new Task();
        t5.setTask("Repeat");
        taskRepository.save(t5);

        System.out.println("tasks Saved: "+ taskRepository.count());

    }
}
