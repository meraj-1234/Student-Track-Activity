package com.studenttrack.controller;

import com.studenttrack.model.Task;
import com.studenttrack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long taskId, @RequestParam String status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long studentId) {
        return ResponseEntity.ok(taskService.getTasksByStudentId(studentId));
    }
}
