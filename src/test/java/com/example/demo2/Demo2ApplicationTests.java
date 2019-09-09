package com.example.demo2;

import com.example.demo2.domain.Task;
import com.example.demo2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class Demo2ApplicationTests{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	@Test
	void taskRepositoryIsSavingTask() throws Exception {
		Task t1 = new Task();
		t1.setId(1);
		t1.setTask("Bowling");

		Task t2 = new Task();
		t2.setId(2);
		t2.setTask("Dancing");

		List<Task> tasks = new ArrayList<>();
		tasks.add(t1);
		tasks.add(t2);
		given(taskService.findAllTasks()).willReturn(tasks);
		this.mockMvc.perform(get("/api/v1/tasks"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'id': 1,'task':'Bowling'},{'id': 2,'task':'Dancing'}]"));
	}


}
