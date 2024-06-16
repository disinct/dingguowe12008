package com.ruoyi.example.service.impl;

import com.ruoyi.common.exception.BizException;
import com.ruoyi.example.service.IStudentService;
import com.ruoyi.resturant.domain.Student;
import com.ruoyi.resturant.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudents() {

        return  studentMapper.queryAll();
    }

    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int deleteStudent(Long id) {
        //根据学生ID查询数据库中是否存在该学生，不存在则异常，存在则删除
        Student student = studentMapper.selectStudentById(id);
        if (student == null){
            throw new BizException(404,"该学生ID"+id+"不存在");
        }
        log.info("该学生id" + id + "存在");
        return studentMapper.deleteStudent(id);
    }
}
