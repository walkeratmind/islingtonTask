package com.rakesh.islingtonTask.repository;

import com.rakesh.islingtonTask.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findTeacherByName(String name);
}
