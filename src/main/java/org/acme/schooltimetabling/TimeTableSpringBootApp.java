package org.acme.schooltimetabling;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.acme.schooltimetabling.domain.Lesson;
import org.acme.schooltimetabling.domain.Room;
import org.acme.schooltimetabling.domain.Timeslot;
import org.acme.schooltimetabling.persistence.LessonRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.acme.schooltimetabling.persistence.TimeslotRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class TimeTableSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(TimeTableSpringBootApp.class, args);
    }

    @Value("${timeTable.demoData:SMALL}")
    private DemoData demoData;

    @Bean
    public CommandLineRunner demoData(
            TimeslotRepository timeslotRepository,
            RoomRepository roomRepository,
            LessonRepository lessonRepository) {
        return (args) -> {
            if (demoData == DemoData.NONE) {
                return;
            }

            // Timeslots
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(9, 0)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(10, 0)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0)));

            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(9, 0)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(10, 0)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(11, 0)));

            // Rooms com capacidade
            roomRepository.save(new Room("Room A", 30));
            roomRepository.save(new Room("Room B", 25));
            roomRepository.save(new Room("Room C", 40));
            if (demoData == DemoData.LARGE) {
                roomRepository.save(new Room("Room D", 50));
                roomRepository.save(new Room("Room E", 20));
                roomRepository.save(new Room("Room F", 35));
            }

            // Lessons com número de alunos
            lessonRepository.save(new Lesson("Math", "A. Turing", "9th grade", 28));
            lessonRepository.save(new Lesson("Physics", "M. Curie", "9th grade", 25));
            lessonRepository.save(new Lesson("Chemistry", "M. Curie", "9th grade", 22));
            lessonRepository.save(new Lesson("English", "I. Jones", "9th grade", 30));
            lessonRepository.save(new Lesson("Spanish", "P. Cruz", "9th grade", 20));
            lessonRepository.save(new Lesson("Biology", "C. Darwin", "9th grade", 26));
            if (demoData == DemoData.LARGE) {
                lessonRepository.save(new Lesson("ICT", "A. Turing", "9th grade", 32));
                lessonRepository.save(new Lesson("History", "I. Jones", "9th grade", 18));
                lessonRepository.save(new Lesson("Art", "S. Dali", "9th grade", 25));
                lessonRepository.save(new Lesson("PE", "C. Lewis", "9th grade", 35));
            }

            lessonRepository.save(new Lesson("Math", "A. Turing", "10th grade", 27));
            lessonRepository.save(new Lesson("Geography", "C. Darwin", "10th grade", 24));
            lessonRepository.save(new Lesson("English", "P. Cruz", "10th grade", 30));
            lessonRepository.save(new Lesson("Spanish", "P. Cruz", "10th grade", 21));
            if (demoData == DemoData.LARGE) {
                lessonRepository.save(new Lesson("ICT", "A. Turing", "10th grade", 33));
                lessonRepository.save(new Lesson("Drama", "I. Jones", "10th grade", 22));
            }

            // Vincular uma aula inicial com room e timeslot
            Lesson lesson = lessonRepository.findAll(Sort.by("id")).iterator().next();
            lesson.setTimeslot(timeslotRepository.findAll(Sort.by("id")).iterator().next());
            lesson.setRoom(roomRepository.findAll(Sort.by("id")).iterator().next());

            lessonRepository.save(lesson);
        };
    }

    public enum DemoData {
        NONE,
        SMALL,
        LARGE
    }

}
