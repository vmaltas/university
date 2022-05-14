package com.philips.university.configuration;

import com.philips.university.domain.Course;
import com.philips.university.domain.Department;
import com.philips.university.domain.Professor;
import com.philips.university.domain.Schedule;
import com.philips.university.repository.CourseRepository;
import com.philips.university.repository.DepartmentRepository;
import com.philips.university.repository.ProfessorRepository;
import com.philips.university.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final DepartmentRepository departmentRepository;

    private final ProfessorRepository professorRepository;

    private final CourseRepository courseRepository;

    private final ScheduleRepository scheduleRepository;


    public void run(ApplicationArguments args) {

        Department department1 = new Department("Physical Science", null);
        Department department2 = new Department("Clinical Medicine", null);
        Department department3 = new Department("Biological Sciences", null);
        Department department4 = new Department("Arts and Humanities", null);
        Department department5 = new Department("Technology", null);
        Department department6 = new Department("Humanities & Social Sciences", null);

        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);
        departmentRepository.save(department4);
        departmentRepository.save(department5);
        departmentRepository.save(department6);

        Professor professor1 = new Professor("John Doe", department2);
        Professor professor2 = new Professor("Frida Mcintosh", department2);
        Professor professor3 = new Professor("Grace Avery", department1);
        Professor professor4 = new Professor("Ada Osborne", department3);
        Professor professor5 = new Professor("Rowan Graves", department1);
        Professor professor6 = new Professor("Selena Owen", department5);
        Professor professor7 = new Professor("Sarahi Barry", department2);
        Professor professor8 = new Professor("Camden Lin", department1);
        Professor professor9 = new Professor("Daniel Hicks", department5);
        Professor professor10 = new Professor("Timothy Hickman", department4);

        professorRepository.save(professor1);
        professorRepository.save(professor2);
        professorRepository.save(professor3);
        professorRepository.save(professor4);
        professorRepository.save(professor5);
        professorRepository.save(professor6);
        professorRepository.save(professor7);
        professorRepository.save(professor8);
        professorRepository.save(professor9);
        professorRepository.save(professor10);

        Course course1 = new Course("Pure Mathematics and Mathematical Statistics", department1, 3);
        Course course2 = new Course("Applied Mathematics and Theoretical Physics", department1, 5);
        Course course3 = new Course("Earth Science", department1, 7);
        Course course4 = new Course("Astronomy", department1, 6);
        Course course5 = new Course("Physics", department1, 8);
        Course course6 = new Course("Geography", department1, 7);
        Course course7 = new Course("Materials Science and Metallurgy ", department1, 5);
        Course course8 = new Course("Chemistry", department1, 1);
        Course course9 = new Course("Clinical Biochemistry", department2, 3);
        Course course10 = new Course("Clinical Neuroscience", department2, 5);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);
        courseRepository.save(course7);
        courseRepository.save(course8);
        courseRepository.save(course9);
        courseRepository.save(course10);

        scheduleRepository.save(new Schedule(professor5, course3, 6, 2012));
        scheduleRepository.save(new Schedule(professor7, course3, 1, 2013));
        scheduleRepository.save(new Schedule(professor5, course7, 6, 2010));
        scheduleRepository.save(new Schedule(professor2, course10, 2, 2004));
        scheduleRepository.save(new Schedule(professor5, course1, 1, 2011));
        scheduleRepository.save(new Schedule(professor2, course9, 4, 2005));
        scheduleRepository.save(new Schedule(professor7, course10, 6, 2009));
        scheduleRepository.save(new Schedule(professor5, course6, 4, 2007));
        scheduleRepository.save(new Schedule(professor7, course9, 1, 2014));
        scheduleRepository.save(new Schedule(professor9, course9, 5, 2011));

    }
}