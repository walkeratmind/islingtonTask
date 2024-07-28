package com.rakesh.islingtonTask.controller;

import com.rakesh.islingtonTask.dto.GroupDTO;
import com.rakesh.islingtonTask.service.interfaces.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final IGroupService groupService;

    @Autowired
    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/workload")
    public ResponseEntity<GroupDTO> getGroupTotalHours(
            @RequestParam Integer groupId) {
        return ResponseEntity.ok(groupService.getGroupTotalWorkHours(groupId));
    }
}
