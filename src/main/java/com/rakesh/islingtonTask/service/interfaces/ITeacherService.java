package com.rakesh.islingtonTask.service.interfaces;

import com.rakesh.islingtonTask.dto.TeacherDTO;
import com.rakesh.islingtonTask.entity.Teacher;

import java.util.Date;

public interface ITeacherService {
    Teacher getTeacherById(int teacherId);
    TeacherDTO getTeacherWorkHours(String teacherName, Date dateFrom, Date dateTo);
}
