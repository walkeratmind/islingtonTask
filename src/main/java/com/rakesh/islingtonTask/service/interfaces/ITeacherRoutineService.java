package com.rakesh.islingtonTask.service.interfaces;

import java.util.Date;

public interface ITeacherRoutineService {
    Float calculateTeacherTotalWorkHours(String teacherName, Date dateFrom, Date dateTo);
}
