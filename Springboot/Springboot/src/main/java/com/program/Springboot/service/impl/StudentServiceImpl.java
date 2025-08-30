package com.program.Springboot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

// import org.springframework.stereotype.Service;

import com.program.Springboot.model.Student;
import com.program.Springboot.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {

    // Simulated database (key = pkStudentID, value = Student object)
    private final Map<Long, Student> studentDB = new HashMap<>();
    private long idCounter = 0000001;

    @Override
    public List<Student> getAllStudents(Student student) {
        // return all values as a List
        return new ArrayList<>(studentDB.values());
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        // wrap in Optional to avoid null pointer exceptions
        return Optional.ofNullable(studentDB.get(id));
    }

    @Override
    public Student addStudent(Student student) {

        if(student.getPkStudentID() == 0) {
            student.setPkStudentID(idCounter++);
        }
        studentDB.put(student.getPkStudentID(), student);

        return studentDB.get(student.getPkStudentID());
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        if (studentDB.containsKey(id)) {
            // update existing record
            Student student = studentDB.get(id);
            student.setName(updatedStudent.getName());
            student.setCourse(updatedStudent.getCourse());

            // save back to map (not strictly necessary since object is reference)
            studentDB.put(id, student);
            return student;
        } else {
            return null; // controller can translate this into 404 NOT FOUND
        }
    }

    @Override
    public boolean deleteStudent(Long id) {
        if (studentDB.containsKey(id)) {
            studentDB.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(studentDB.values());
    }
    
}