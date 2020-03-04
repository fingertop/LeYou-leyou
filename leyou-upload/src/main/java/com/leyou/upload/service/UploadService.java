package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
@Slf4j
@EnableConfigurationProperties({UploadProperties.class})
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Autowired
    private  UploadProperties properties;


//    private static  final List<String> allowTypes =
//            Arrays.asList("image/jpeg","image/png","image/bmp","image/jpg");

    public String getImageUrl(MultipartFile file) {
        try {
            //校验文件安全性
            String type = file.getContentType();
                //校验文件后缀名
//                if (!allowTypes.contains(type)) {
                if (!properties.getAllowTypes().contains(type)) {
                    throw new LeyouException(ExceptionEnums.file_type_invalid);
                }

                //校验文件内容
                BufferedImage imageInvalid = ImageIO.read(file.getInputStream());
                    //非图片则返回空
                if (imageInvalid == null) {
                    throw new LeyouException(ExceptionEnums.file_type_invalid);
                }
                

//            //准备目标文件
//            File dest= new File("D:\\java\\leyoumall",file.getOriginalFilename());
//
//            //保存文件   即转换文件 于 dest文件中
//            file.transferTo(dest);

            //上传至fastDFS

                //截取后缀名
                //String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                //推荐写法效率高
                String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
                StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extension, null);


            //返回路径
               //return "http://image.leyou.com/"+file.getOriginalFilename();

             //return "http://image.leyou.com/"+storePath.getFullPath();
              //域名抽取 见配置文件 再通过UploadProperties读取
            return properties.getBaseUrl()+storePath.getFullPath();

        } catch (IOException e) {
            //上传失败记录日志
            log.error("[fileupload] 上传文件失败",e);

            throw new LeyouException(ExceptionEnums.upload_failed);
        }

    }


}
