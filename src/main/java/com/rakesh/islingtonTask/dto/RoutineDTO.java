package com.rakesh.islingtonTask.dto;

import com.rakesh.islingtonTask.entity.Routine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoutineDTO {
    private Integer routineId;
    private Time startTime;
    private Time endTime;
    private Date routineDate;
    private String subject;
    private Integer duration;
    private Integer teacherId;
    private Integer groupId;

    public RoutineDTO(Routine routine) {
        this.routineId = routine.getRoutineId();
        this.startTime = routine.getStartTime();
        this.endTime = routine.getEndTime();
        this.routineDate = routine.getRoutineDate();
        this.subject = routine.getSubject();
        this.duration = routine.getDuration();
        this.teacherId = routine.getTeacher().getTeacherId();
        this.groupId = routine.getGroup().getGroupId();
    }
}
