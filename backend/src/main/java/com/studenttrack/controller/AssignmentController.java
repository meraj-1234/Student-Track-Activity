package com.studenttrack.controller;

import com.studenttrack.model.Assignment;
import com.studenttrack.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.addAssignment(assignment));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Assignment>> getAssignments(@PathVariable Long studentId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByStudentId(studentId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Assignment> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(assignmentService.updateStatus(id, status));
    }
}
