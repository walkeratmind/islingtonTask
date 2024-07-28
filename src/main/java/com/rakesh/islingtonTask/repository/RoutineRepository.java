package com.rakesh.islingtonTask.repository;

import com.rakesh.islingtonTask.entity.Routine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    @EntityGraph(attributePaths = {"teacher", "group"})
    List<Routine> findAll();

    @Query("SELECT r FROM Routine r JOIN r.teacher t WHERE t.name = :teacherName AND r.routineDate BETWEEN :startDate AND :endDate")
    @EntityGraph(attributePaths = {"teacher"})
    List<Routine> findRoutineByTeacherAndDateRange(String teacherName, Date startDate, Date endDate);

    @Query("""
            SELECT r FROM Routine r 
            JOIN FETCH r.teacher t 
            WHERE t.teacherId = :teacherId 
              AND r.routineDate = :routineDate 
              AND :startTime < r.endTime 
              AND :endTime > r.startTime
            """)
    Routine getRoutineByTeacherIdAndTime(@Param("teacherId") Integer teacherId,
                                         @Param("routineDate") Date routineDate,
                                         @Param("startTime") Time startTime,
                                         @Param("endTime") Time endTime);

}
