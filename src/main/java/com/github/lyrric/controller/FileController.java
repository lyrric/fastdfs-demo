package com.github.lyrric.controller;

import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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


}
