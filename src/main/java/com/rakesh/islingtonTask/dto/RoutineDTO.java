package com.rakesh.islingtonTask.dto;

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
}
