package com.ruoyi.web.controller.resturant;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.resturant.domain.Student;
import com.ruoyi.web.controller.example.StudentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    //
    @Test
    public void testgetStudentList(){
        AjaxResult result = studentController.getStudentList();
        Assertions.assertTrue(result.isSuccess());

        List<Student> list = (List<Student>)result.get("data");
        Assertions.assertEquals(3,list.size());
    }

    //根据学生ID查询学生详细信息
    @Test
    public void testsecectStudentByID(){
        AjaxResult result = studentController.selectStudentById(2L);
        Assertions.assertTrue(result.isSuccess());

        Student student = (Student) result.get("data");
        Assertions.assertEquals("李四", student.getName());

    }
}
