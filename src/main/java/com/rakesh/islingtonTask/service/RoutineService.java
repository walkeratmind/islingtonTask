package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.RoutineRepository;
import com.rakesh.islingtonTask.service.interfaces.IRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RoutineService implements IRoutineService {
    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Float calculateTeacherTotalWorkHours(String teacherName, Date dateFrom, Date dateTo) {
        List<Routine> routines = this.routineRepository.findRoutineByTeacherAndDateRange(teacherName, dateFrom, dateTo);
        if (routines.isEmpty()) {
            throw new ResourceNotFoundException("No routines found for the specified teacher and date range");
        }
        AtomicInteger totalWorkMinutes = new AtomicInteger();
        routines.forEach(routine -> {
            Duration duration = Duration.between(routine.getStartTime().toLocalTime(), routine.getEndTime().toLocalTime());
            totalWorkMinutes.addAndGet((int) duration.toMinutes());
        });
        return totalWorkMinutes.get() / 60.0f;
    }
}
