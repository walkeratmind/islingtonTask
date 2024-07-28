package com.rakesh.islingtonTask.entity;

import com.rakesh.islingtonTask.listener.RoutineListener;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(RoutineListener.class)
@Table(name = "routine")
public class Routine extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Integer routineId;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "routine_date")
    private Date routineDate;

    @Column(name = "subject")
    private String subject;

    @Column(name = "duration")
    private Integer duration; // duration in minutes

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    // Method to calculate duration
    public void calculateDuration() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime.toLocalTime(), endTime.toLocalTime());
            this.duration = (int) duration.toMinutes();
        }
    }
}

