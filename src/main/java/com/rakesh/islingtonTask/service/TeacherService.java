package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.dto.TeacherDTO;
import com.rakesh.islingtonTask.entity.Teacher;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.TeacherRepository;
import com.rakesh.islingtonTask.service.interfaces.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;

    // using service as dependency instead of routineRepository for best practice
    private final RoutineService routineService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, RoutineService routineService) {
        this.teacherRepository = teacherRepository;
        this.routineService = routineService;
    }

    @Override
    public TeacherDTO getTeacherWorkHours(String teacherName, Date dateFrom, Date dateTo) {
        Teacher teacher = this.teacherRepository.findTeacherByName(teacherName)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with that name"));

        TeacherDTO teacherDto = new TeacherDTO(teacher);
        teacherDto.setTotalWorkHours(this.routineService.calculateTeacherTotalWorkHours(teacherName, dateFrom, dateTo));

        return teacherDto;
    }
}
