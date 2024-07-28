package com.rakesh.islingtonTask.service.interfaces;

import com.rakesh.islingtonTask.dto.GroupDTO;
import com.rakesh.islingtonTask.entity.Group;

public interface IGroupService {

    Group getGroupById(int id);

    GroupDTO getGroupTotalWorkHours(int groupId);
}
