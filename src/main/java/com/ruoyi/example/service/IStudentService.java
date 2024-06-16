package com.ruoyi.example.service;

import com.ruoyi.resturant.domain.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> getStudents();

    public Student selectStudentById(Long id);
    public int insertStudent(Student student);
    public int updateStudent(Student student);
    public int deleteStudent(Long id);
}
