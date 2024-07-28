package com.rakesh.islingtonTask.repository;

import com.rakesh.islingtonTask.entity.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("Select g from Group g WHERE g.groupId = :id")
    @EntityGraph(attributePaths = {"routines"})
    Optional<Group> getGroupById(int id);
}
