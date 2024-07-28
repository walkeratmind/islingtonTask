package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.constants.TimeConstants;
import com.rakesh.islingtonTask.dto.TeacherDTO;
import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.entity.Teacher;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.TeacherRepository;
import com.rakesh.islingtonTask.service.interfaces.ITeacherRoutineService;
import com.rakesh.islingtonTask.service.interfaces.ITeacherService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Slf4j
@Service
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;

    // using service as dependency instead of routineRepository for best practice
    private final ITeacherRoutineService teacherRoutineService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, TeacherRoutineService teacherRoutineService) {
        this.teacherRepository = teacherRepository;
        this.teacherRoutineService = teacherRoutineService;
    }

    @Override
    @Transactional
    public TeacherDTO getTeacherWorkHours(String teacherName, Date dateFrom, Date dateTo) {
        Teacher teacher = this.teacherRepository.getTeacherByName(teacherName)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with that name"));

        Set<Routine> routines = teacher.getRoutines();
        // filter routines and get totalWorkMinutes
        int totalWorkMinutes = routines.stream()
                .filter(routine -> !routine.getRoutineDate().before(dateFrom) && !routine.getRoutineDate().after(dateTo))
                .mapToInt(Routine::getDuration).sum();

        TeacherDTO teacherDto = new TeacherDTO(teacher);
        teacherDto.setTotalWorkHours(totalWorkMinutes / TimeConstants.MINUTES_PER_HOUR);

        return teacherDto;

//        TeacherDTO teacherDto = new TeacherDTO(teacher);
//        teacherDto.setTotalWorkHours(this.teacherRoutineService.calculateTeacherTotalWorkHours(teacherName, dateFrom, dateTo));
//
//        return teacherDto;
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return this.teacherRepository.getReferenceById(teacherId);
    }
}
