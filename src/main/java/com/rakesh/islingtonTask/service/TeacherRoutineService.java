package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.constants.TimeConstants;
import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.RoutineRepository;
import com.rakesh.islingtonTask.service.interfaces.ITeacherRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TeacherRoutineService implements ITeacherRoutineService {
    private final RoutineRepository routineRepository;

    @Autowired
    public TeacherRoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public Float calculateTeacherTotalWorkHours(String teacherName, Date dateFrom, Date dateTo) {
        List<Routine> routines = this.routineRepository.findRoutineByTeacherAndDateRange(teacherName, dateFrom, dateTo);
        if (routines.isEmpty()) {
            throw new ResourceNotFoundException("No routines found for the specified teacher and date range");
        }
        int totalWorkMinutes = routines.stream().mapToInt(Routine::getDuration).sum();

        return totalWorkMinutes / TimeConstants.MINUTES_PER_HOUR;
    }
}
