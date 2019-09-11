package com.example.demo2.bootstrap;

import com.example.demo2.domain.Task;
import com.example.demo2.repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class BootStrapData implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public BootStrapData(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Logger logger = Logger.getLogger(String.valueOf(BootStrapData.class));

    @Override
    public void run(String... args) throws Exception {

        logger.info("Loading Tasks");

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

        logger.info("tasks Saved: "+ taskRepository.count());

    }
}
