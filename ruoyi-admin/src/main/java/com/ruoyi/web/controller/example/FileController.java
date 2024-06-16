package com.ruoyi.web.controller.example;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "文件操作")
@Anonymous
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${ruoyi.profile}")
    private String uploadFolder;
    //文件上传
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public AjaxResult upload(@RequestPart("file") MultipartFile file) {
        // 1 判断文件是否为空，是空的话抛出异常
        if (file.isEmpty()){
            throw new BizException(404, "文件不能为空");
        }
        // 2 判断服务器上配置的目录存在不存在，不存在的话，创建该服务器目录
        File path =new File(uploadFolder);
        if (!path.exists()){
            path.mkdirs();
        }
        // 3 在该服务器目录里创建一个同名的空的文件
        String fileName = file.getOriginalFilename();
        File serverFile = new File(uploadFolder + "/" + fileName);
        // 4 前端传过来的文件的内容写入到这个创建同名的空的文件中
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            throw new BizException(500,"文件异常，上传失败");
        }
        return AjaxResult.success("文件上传成功");
    }

    @ApiOperation(value = "文件下载", notes = "下载服务器上文件到本地")
    @ApiImplicitParam(name = "name", value = "文件名", required = true, dataType = "string", paramType = "query",
            dataTypeClass = String.class)
    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam("name") String name) {
        FileSystemResource file = new FileSystemResource(uploadFolder + "/" + name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"",
                file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            throw new BizException(500, "文件下载失败");
        }
    }

}
