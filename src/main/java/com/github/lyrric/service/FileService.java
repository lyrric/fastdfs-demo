package com.github.lyrric.service;

import com.github.lyrric.model.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2019-06-25.
 *
 * @author wangxiaodong
 */
public interface FileService {

    /**
     * 上传
     * @param file
     * @return
     * @throws BusinessException
     */
    Long upload(MultipartFile file) throws BusinessException;

    /**
     * 下载
     * @param id
     * @param response
     */
    void download(Long id, HttpServletResponse response);

    /**
     * 图片预览
     * @param id
     * @param response
     */
    void overview(Long id, HttpServletResponse response);


    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
