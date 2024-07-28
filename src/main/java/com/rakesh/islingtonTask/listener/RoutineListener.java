package com.rakesh.islingtonTask.listener;

import com.rakesh.islingtonTask.entity.Routine;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class RoutineListener {

    @PrePersist
    @PreUpdate
    public void setDuration(Routine routine) {
        routine.calculateDuration();
    }
}

