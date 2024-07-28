package com.rakesh.islingtonTask.dto;

import com.rakesh.islingtonTask.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupDTO {
    private Integer groupId;
    private String groupName;
    private String description;
    private Float totalWorkHours;

    public GroupDTO(Group group) {
        this.groupId = group.getGroupId();
        this.groupName = group.getName();
        this.description = group.getDescription();
    }

}
