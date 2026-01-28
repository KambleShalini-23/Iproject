package com.iproject.backend.repository;

import com.iproject.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Student> findByFirstName( String firstName);

    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.updatedAt IS NOT NULL")
    List<Student> findActiveStudentByFirstName(@Param("firstName") String firstName);
}
