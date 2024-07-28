package com.rakesh.islingtonTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDto {
    private Integer teacherId;
    private String name;
    private String email;
    private String phone;
    private Float totalWorkHours;
}
