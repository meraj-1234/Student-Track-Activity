package com.studenttrack.repository;

import com.studenttrack.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByStudentId(Long studentId);
}
