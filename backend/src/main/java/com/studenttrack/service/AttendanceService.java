package com.studenttrack.service;

import com.studenttrack.model.Attendance;
import com.studenttrack.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public Map<String, Object> getAttendanceSummary(Long studentId) {
        List<Attendance> attendanceList = attendanceRepository.findByStudentId(studentId);
        long totalClasses = attendanceList.size();
        long attendedClasses = attendanceList.stream()
                .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                .count();
        
        double percentage = totalClasses == 0 ? 0 : (double) attendedClasses / totalClasses * 100;

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalClassesHeld", totalClasses);
        summary.put("attendedClasses", attendedClasses);
        summary.put("attendancePercentage", percentage);

        return summary;
    }
}
