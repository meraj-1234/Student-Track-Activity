package com.studenttrack.service;

import com.studenttrack.model.Assignment;
import com.studenttrack.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment addAssignment(Assignment assignment) {
        assignment.setStatus("PENDING");
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByStudentId(Long studentId) {
        return assignmentRepository.findByStudentId(studentId);
    }

    public Assignment updateStatus(Long id, String status) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();
        assignment.setStatus(status);
        return assignmentRepository.save(assignment);
    }
}
