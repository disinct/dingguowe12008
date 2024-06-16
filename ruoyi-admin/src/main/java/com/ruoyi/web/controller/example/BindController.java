package com.ruoyi.web.controller.example;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.example.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
@Api(tags = "参数绑定")
@Anonymous
@RestController
@RequestMapping("/bind")
public class BindController {
@ApiOperation("url参数")
@ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "用户id", dataType = "true", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "sex", value = "用户名称", dataType = "String", dataTypeClass = String.class)
})
        @GetMapping("/user")
        public String getUserName(  @RequestParam("name") String name,
                                    @RequestParam(value="sex",required = false) String sex) {
            return name + " " + sex;
        }

    public String getUserId() {
        return "";
    }
    @ApiOperation("取得用户ID")
    @ApiImplicitParam(name = "ID", value = "用户ID", dataType = "path",required = true, dataTypeClass = Integer.class)
    @GetMapping("/ids/{id}")
    public String getUserId(@PathVariable("id") String userId) {
        return userId;
    }
    @ApiOperation("新增用户")
    @PostMapping("/users")
    public AjaxResult insertUser(@RequestBody User user) {
        return AjaxResult.success(user);
    }

}
