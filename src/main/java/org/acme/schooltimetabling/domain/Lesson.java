package org.acme.schooltimetabling.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
@Entity
public class Lesson {

    @PlanningId
    @Id @GeneratedValue
    private Long id;

    private String subject;
    private String teacher;
    private String studentGroup;

    @PlanningVariable
    @ManyToOne
    private Timeslot timeslot;

    @PlanningVariable
    @ManyToOne
    private Room room;

    private int studentCount;

    // No-arg constructor required for Hibernate and OptaPlanner
    public Lesson() {
    }

    public Lesson(String subject, String teacher, String studentGroup, int studentCount) {
    this.subject = subject;
    this.teacher = teacher;
    this.studentGroup = studentGroup;
    this.studentCount = studentCount;
}

public int getStudentCount() {
    return studentCount;
}

public void setStudentCount(int studentCount) {
    this.studentCount = studentCount;
}

    public Lesson(long id, String subject, String teacher, String studentGroup, int studentCount, Timeslot timeslot, Room room) {
        this(subject, teacher, studentGroup, studentCount);
        this.id = id;
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
