package com.rakesh.islingtonTask.controller;

import com.rakesh.islingtonTask.dto.TeacherDTO;
import com.rakesh.islingtonTask.service.interfaces.ITeacherService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final ITeacherService teacherService;

    @Autowired
    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/work_hours")
    public ResponseEntity<TeacherDTO> getTeacherWorkHours(
            @RequestParam String teacherName,
            @Valid @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @Valid @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws BadRequestException {

        if (startDate.after(endDate)) {
            throw new BadRequestException("Start date cannot be after end date");
        }
        return ResponseEntity.ok(teacherService.getTeacherWorkHours(teacherName, startDate, endDate));
    }
}
