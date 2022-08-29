package com.xn2001.college.service.oss.controller;


import com.junbin.common.utils.R;
import com.xn2001.college.service.oss.service.FileService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @author 乐心湖
 * @date 2020/6/30 23:10
 **/
//@Api(tags = "阿里云文件管理")
@RestController
@RequestMapping("/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
//    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
//            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file,
//            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module
    ) {
        try {
            InputStream inputStream = file.getInputStream();
            //获取上传文件的原文件名
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            //返回

            return R.ok("文件上傳成功").put("url", uploadUrl);
        } catch (Exception e) {
            return R.error("文件上傳成功");
        }
    }

//    @ApiOperation("文件删除")
    @DeleteMapping("remove")
    public R removeFile(
//            @ApiParam(value = "要删除的文件路径", required = true)
            @RequestBody String url) {
        fileService.removeFile(url);
        return R.ok().put("data","文件刪除成功");
    }
}
