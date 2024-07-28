package com.rakesh.islingtonTask.service.interfaces;

import com.rakesh.islingtonTask.dto.TeacherDTO;

import java.util.Date;

public interface ITeacherService {
    TeacherDTO getTeacherWorkHours(String teacherName, Date dateFrom, Date dateTo);
}
