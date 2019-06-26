package com.github.lyrric.controller;

import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxiaodong
 * Created by wangxiaodong on 2018/6/6.
 */
@RestController
@RequestMapping(value = "/file")
@Api(value = "文件管理")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    Long upload(MultipartFile file) throws BusinessException {
        return fileService.upload(file);
    }

    @ApiOperation(value = "下载文件(这个接口用swagger测，会有文件名异常的BUG)")
    @GetMapping(value = "/download/{id}")
    void download(@PathVariable(name = "id") Long id, HttpServletResponse response){
        fileService.download(id, response);
    }
    @ApiOperation(value = "图片浏览")
    @GetMapping(value = "/overview/{id}")
    void overview(@PathVariable(name = "id") Long id, HttpServletResponse response){
        fileService.overview(id, response);
    }
}
