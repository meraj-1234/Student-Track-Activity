package com.studenttrack.service;

import com.studenttrack.model.Task;
import com.studenttrack.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus("PENDING");
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStudentId(Long studentId) {
        return taskRepository.findByStudentId(studentId);
    }
}
