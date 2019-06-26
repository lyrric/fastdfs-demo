package com.github.lyrric.service.impl;

import com.github.lyrric.entity.FastdfsFile;
import com.github.lyrric.mapper.FastdfsFileMapper;
import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.FileService;
import com.github.lyrric.util.FastdfsUtil;
import com.github.lyrric.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2019-06-25.
 *
 * @author wangxiaodong
 */
@Service
public class FileServiceImpl implements FileService {

    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private FastdfsFileMapper fileMapper;
    @Resource
    private FastdfsUtil fastdfsUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long upload(MultipartFile file) throws BusinessException {
        String fileOriginalName = file.getOriginalFilename();
        String ext = fileOriginalName.substring(fileOriginalName.indexOf(".")+1);
        String path;
        try {
            path = fastdfsUtil.upload(file.getBytes(), file.getSize(), ext);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("上传文件失败");
        }
        FastdfsFile fastdfsFile = new FastdfsFile();
        fastdfsFile.setDeleted(false);
        fastdfsFile.setDeleteTime(null);
        fastdfsFile.setFileName(file.getOriginalFilename());
        fastdfsFile.setUploadTime(new Date());
        fastdfsFile.setPath(path);
        fileMapper.insert(fastdfsFile);
        logger.info("上传文件，id：".concat(fastdfsFile.getId().toString()).concat("||path：").concat(path));
        return fastdfsFile.getId();
    }

    @Override
    public void download(Long id, HttpServletResponse response) {
        FastdfsFile fastdfsFile = fileMapper.selectByPrimaryKey(id);
        if(fastdfsFile != null){
            byte[] data = fastdfsUtil.download(fastdfsFile.getPath());
            IOUtil.download(response, data, fastdfsFile.getFileName());
        }
    }

    @Override
    public void overview(Long id, HttpServletResponse response) {
        FastdfsFile fastdfsFile = fileMapper.selectByPrimaryKey(id);
        if(fastdfsFile != null && isPicture(fastdfsFile.getFileName())){
            byte[] data = fastdfsUtil.download(fastdfsFile.getPath());
            IOUtil.overview(response, data);
        }
    }

    /**
     * 判断文件是否是图片
     * @param fileName
     * @return
     */
    private boolean isPicture(String fileName){
        String regex = ".*\\.(gif|jpg|jpeg|png|GIF|JPG|JPEG|PNG|bmp|BMP)";
        Matcher m = Pattern.compile(regex).matcher(fileName);
        return m.matches();
    }
}
