package com.rakesh.islingtonTask.service;

import com.rakesh.islingtonTask.constants.TimeConstants;
import com.rakesh.islingtonTask.dto.GroupDTO;
import com.rakesh.islingtonTask.entity.Group;
import com.rakesh.islingtonTask.entity.Routine;
import com.rakesh.islingtonTask.repository.GroupRepository;
import com.rakesh.islingtonTask.service.interfaces.IGroupService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group getGroupById(int id) {
        return groupRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public GroupDTO getGroupTotalWorkHours(int groupId) {
        Group group = this.getGroupById(groupId);

        Set<Routine> routines = group.getRoutines();

        int totalWorkingMinutes = routines.stream().mapToInt(Routine::getDuration).sum();

        GroupDTO groupDTO = new GroupDTO(group);
        groupDTO.setTotalWorkHours(totalWorkingMinutes / TimeConstants.MINUTES_PER_HOUR);

        return groupDTO;
    }

}
