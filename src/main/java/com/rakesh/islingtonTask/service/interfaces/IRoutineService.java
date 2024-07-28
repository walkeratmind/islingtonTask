package com.rakesh.islingtonTask.service.interfaces;

import com.rakesh.islingtonTask.dto.RoutineDTO;
import com.rakesh.islingtonTask.dto.RoutineRequestDTO;

import java.util.List;

public interface IRoutineService {
    RoutineDTO saveRoutine(RoutineRequestDTO routineRequest);

    List<RoutineDTO> getAllRoutines();

    RoutineDTO getRoutineById(Integer routineId);
}
