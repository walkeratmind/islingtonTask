package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.constants.TimeConstants;
import com.rakesh.islingtonTask.dto.TeacherDTO;
import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.entity.Teacher;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.TeacherRepository;
import com.rakesh.islingtonTask.service.interfaces.ITeacherService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public TeacherDTO getTeacherWorkHours(String teacherName, Date dateFrom, Date dateTo) {
        Teacher teacher = this.teacherRepository.getTeacherByName(teacherName)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with name: " + teacherName));

        List<Routine> routines = teacher.getRoutines();
        // filter routines and get totalWorkMinutes
        int totalWorkMinutes = routines.stream()
                .filter(routine -> !routine.getRoutineDate().before(dateFrom) && !routine.getRoutineDate().after(dateTo))
                .mapToInt(Routine::getDuration).sum();

        TeacherDTO teacherDto = new TeacherDTO(teacher);
        teacherDto.setTotalWorkHours(totalWorkMinutes / TimeConstants.MINUTES_PER_HOUR);

        return teacherDto;
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return this.teacherRepository.getReferenceById(teacherId);
    }
}
