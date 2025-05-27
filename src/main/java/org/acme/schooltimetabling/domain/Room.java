package org.acme.schooltimetabling.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.optaplanner.core.api.domain.lookup.PlanningId;

@Entity
public class Room {

    @PlanningId
    @Id @GeneratedValue
    private Long id;

    private int capacity;

    private String name;

    // No-arg constructor required for Hibernate
    public Room() {
    }

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(long id, String name, int capacity) {
        this(name, capacity);
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
    return capacity;
    }

    public void setCapacity(int capacity) {
    this.capacity = capacity;
    }

}
