package com.ruoyi.resturant.mapper;

import com.ruoyi.resturant.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    //查询所有学生信息
    public List<Student> queryAll();
    public Student selectStudentById(Long id);
    public int insertStudent(Student student);
    public int updateStudent(Student student);
    public int deleteStudent(Long id);
}
