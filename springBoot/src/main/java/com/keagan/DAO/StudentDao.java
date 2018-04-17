package com.keagan.DAO;

import com.keagan.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kbc on 19/06/2017.
 */
@Repository
public class StudentDao {

    public static HashMap<Integer, Student> students;

    static {
        students = new HashMap<Integer, Student>(){

            {
                put(1, new Student(1, "Bill", "Computer Science"));
                put(2, new Student(2, "Phil", "Finance"));
                put(3, new Student(3, "Jill", "Maths"));

            }
        };
    }

    //get students form hashmap and give it to us
    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    public Student getStudentById(int id){
        return this.students.get(id);
    }

    public void removeStudentById(int id) {
        this.students.remove(id);
    }



    public void updateStudent(Student student){
        Student s = students.get(student.getId());
        s.setCourse(student.getCourse());
        s.setCourse(student.getName());
        students.put(student.getId(), student);
    }

    public void insertStudentToDb(Student student) {
        this.students.put(student.getId(),student);
    }

    public void addStudent(Student student){
        Student s = students.get(student.getId());
        s.setId(student.getId());
        s.setCourse(student.getCourse());
        s.setCourse(student.getName());
        students.put(student.getId(), student);
    }

    public HashMap<Integer, Student> getPeopleMap() {
        return students;
    }
}
