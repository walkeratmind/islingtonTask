package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.dto.RoutineDTO;
import com.rakesh.islingtonTask.dto.RoutineRequestDTO;
import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.exception.BadParameterException;
import com.rakesh.islingtonTask.exception.ConflictException;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.repository.RoutineRepository;
import com.rakesh.islingtonTask.service.interfaces.IGroupService;
import com.rakesh.islingtonTask.service.interfaces.IRoutineService;
import com.rakesh.islingtonTask.service.interfaces.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoutineService implements IRoutineService {
    private final RoutineRepository routineRepository;

    private final IGroupService groupService;
    private final ITeacherService teacherService;

    @Autowired
    public RoutineService(RoutineRepository routineRepository,
                          IGroupService groupService,
                          ITeacherService teacherService) {
        this.routineRepository = routineRepository;
        this.groupService = groupService;
        this.teacherService = teacherService;
    }

    @Override
    public RoutineDTO saveRoutine(RoutineRequestDTO routineRequest) {
        if (routineRequest.getStartTime().after(routineRequest.getEndTime())) {
            throw new BadParameterException("Start time cannot be after end time");
        }

        Routine routine = routineRequest.getRoutineEntity();
        // check if teacher is assigned between provided startTime and endTime
        validateIfTeacherHaveSameSlotAssigned(routineRequest);
        routine.setTeacher(this.teacherService.getTeacherById(routineRequest.getTeacherId()));
        routine.setGroup(this.groupService.getGroupById(routineRequest.getGroupId()));
        Routine savedRoutine = this.routineRepository.save(routine);
        return new RoutineDTO(savedRoutine);
    }

    public void validateIfTeacherHaveSameSlotAssigned(RoutineRequestDTO routineRequest) {
        Routine routine = this.routineRepository
                .getRoutineByTeacherIdAndTime(routineRequest.getTeacherId(),
                        routineRequest.getRoutineDate(),
                        routineRequest.getStartTime(),
                        routineRequest.getEndTime());
        if (routine != null) {
            String exceptionMsg = String.format(
                    "Teacher has already been assigned between startTime: %s and endTime: %s",
                    routine.getStartTime(), routine.getEndTime()
            );
            throw new ConflictException(exceptionMsg);

        }
    }

    @Override
    public List<RoutineDTO> getAllRoutines() {
        return this.routineRepository.findAll().stream().map(RoutineDTO::new).collect(Collectors.toList());
    }

    @Override
    public RoutineDTO getRoutineById(Integer routineId) {
        Routine routine = this.routineRepository.findById(routineId).orElseThrow(() -> new ResourceNotFoundException("Routine with id " + routineId + " not found"));
        return new RoutineDTO(routine);
    }
}
