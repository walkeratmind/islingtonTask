package com.rakesh.islingtonTask.controller;

import com.rakesh.islingtonTask.dto.RoutineDTO;
import com.rakesh.islingtonTask.dto.RoutineRequestDTO;
import com.rakesh.islingtonTask.exception.ResourceNotFoundException;
import com.rakesh.islingtonTask.service.interfaces.IRoutineService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final IRoutineService routineService;

    @Autowired
    public RoutineController(IRoutineService routineService) {
        this.routineService = routineService;
    }

    @PostMapping
    public ResponseEntity<RoutineDTO> saveRoutine(@Valid @RequestBody RoutineRequestDTO routineRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routineService.saveRoutine(routineRequest));
    }

    @GetMapping
    public ResponseEntity<List<RoutineDTO>> fetchAllRoutines() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routineService.getAllRoutines());
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineDTO> getRoutineById(@PathVariable int id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }
}
