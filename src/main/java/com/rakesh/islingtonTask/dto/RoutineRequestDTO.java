package com.rakesh.islingtonTask.dto;

import com.rakesh.islingtonTask.entity.Routine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoutineRequestDTO {
    @NotNull(message = "Start time is required")
    private Time startTime;

    @NotNull(message = "End time is required")
    private Time endTime;

    @NotNull(message = "Routine date is required")
    private Date routineDate;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull(message = "Teacher ID is required")
    private Integer teacherId;

    @NotNull(message = "Group ID is required")
    private Integer groupId;

    public Routine getRoutineEntity() {
        Routine routine = new Routine();

        routine.setStartTime(this.startTime);
        routine.setEndTime(this.endTime);
        routine.setRoutineDate(this.routineDate);
        routine.setSubject(this.subject);

        return routine;
    }
}
