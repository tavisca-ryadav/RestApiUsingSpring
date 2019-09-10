package com.example.demo2;

import com.example.demo2.domain.Task;
import com.example.demo2.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskService taskService;

    private Task task1,task2,task3;
    private List<Task> tasks;


    public TaskControllerTest(){
        task1 = new Task();
        task1.setId(1);
        task1.setTask("Bowling");

        task2 = new Task();
        task2.setId(2);
        task2.setTask("Dancing");

        task3 = new Task();
        task3.setId(3);
        task3.setTask("");

        tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    void getAllTasksUsingGETMethod() throws Exception {
        given(taskService.findAllTasks()).willReturn(tasks);
        this.mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'task':'Bowling'},{'id': 2,'task':'Dancing'}]"));
    }

    @Test
    void saveTaskUsingPOSTMethod() throws Exception {
        given(taskService.saveTask(task1)).willReturn(task1);
        this.mockMvc.perform(post("/api/v1/tasks/posts")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(task1)))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':1,'task':'Bowling'}"));
    }

    @Test
    void emptyPostTaskGivesNoContentStatus() throws Exception {
        given(taskService.saveTask(task3)).willReturn(task3);
        this.mockMvc.perform(post("/api/v1/tasks/posts")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(task3)))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Empty task is not allowed"));
    }

    @Test
    void deleteTaskUsingDELETEMethod() throws Exception {
        given(taskService.deleteTask(task2)).willReturn(true);
        this.mockMvc.perform(delete("/api/v1/tasks/deletes")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(task2)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':2,'task':'Dancing'}"));
    }

    @Test
    void emptyDeleteTaskGivesNoContentStatus() throws Exception {
        given(taskService.deleteTask(task3)).willReturn(false);
        this.mockMvc.perform(delete("/api/v1/tasks/deletes")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(task3)))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Task not found"));
    }

    @Test
    void updateTaskUsingPUTMethod() throws Exception {
        given(taskService.updateTask(tasks)).willReturn(true);
        this.mockMvc.perform(put("/api/v1/tasks/puts")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(tasks)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':2,'task':'Dancing'}"));
    }

    @Test
    void updateTaskGivesNotModifiedStatus() throws Exception {
        tasks.remove(task1);
        tasks.add(task3);
        given(taskService.updateTask(tasks)).willReturn(false);
        this.mockMvc.perform(put("/api/v1/tasks/puts")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(tasks)))
                .andExpect(status().isNotModified())
                .andExpect(content().string("Task is empty or not found"));
    }
}