package com.github.lyrric.service;

import com.github.lyrric.model.BusinessException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created on 2019-06-25.
 *
 * @author wangxiaodong
 */
public interface FileService {

    Long upload(MultipartFile file) throws BusinessException;
}
