package com.rakesh.islingtonTask.repository;

import com.rakesh.islingtonTask.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT t FROM Teacher t JOIN FETCH t.routines WHERE t.name = :name")
    Optional<Teacher> getTeacherByName(String name);
}
