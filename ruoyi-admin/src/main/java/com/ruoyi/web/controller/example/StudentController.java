package com.ruoyi.web.controller.example;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BizException;
import com.ruoyi.example.service.IStudentService;
import com.ruoyi.example.service.impl.StudentServiceImpl;
import com.ruoyi.resturant.domain.ResShop;
import com.ruoyi.resturant.domain.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="学生信息管理")
@Anonymous
@RestController
@RequestMapping("/student")
public class StudentController  extends BaseController {
    @Autowired
    private IStudentService studentService;
    @ApiOperation("查询所有的学生")
    @GetMapping("/list")
    public AjaxResult getStudentList() {
        List<Student> list = studentService.getStudents();

        return AjaxResult.success(list);
    }
    //根据学生id查询学生详细
    @ApiOperation(value = "获取学生详细信息", notes = "TODO：取得某个学生的详细信息")
    @ApiImplicitParam(name = "id", value = "学生ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)

    @GetMapping("/{id}")
    public AjaxResult selectStudentById(@PathVariable("id") Long id) {
        Student student = studentService.selectStudentById(id);
        if(student == null){
            throw new BizException(404,"不存在");
        }
        return AjaxResult.success(student);
    }
    @ApiOperation("新增学生")
    @PostMapping
    @Log(title = "学生管理",businessType = BusinessType.INSERT)
    public AjaxResult add(@Validated @RequestBody Student student) {
        return AjaxResult.success(studentService.insertStudent(student));
    }
    @ApiOperation("修改学生")
    @PutMapping
    @Log(title = "学生管理",businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody Student student) {
        Student studentDB = studentService.selectStudentById(student.getId());

        if (studentDB == null){
            throw new BizException(404,"学生不存在");
        }

        int num = studentService.updateStudent(student);
        return AjaxResult.success(num);
    }
    @ApiOperation("删除学生")
    @Log(title = "学生管理",businessType = BusinessType.DELETE)
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {

        return AjaxResult.success(studentService.deleteStudent(id));
    }
}
