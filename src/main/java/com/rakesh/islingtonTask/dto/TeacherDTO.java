package com.rakesh.islingtonTask.dto;

import com.rakesh.islingtonTask.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDTO {
    private Integer teacherId;
    private String name;
    private String email;
    private String phone;
    private Float totalWorkHours;

    public TeacherDTO(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.phone = teacher.getPhone();
    }
}
