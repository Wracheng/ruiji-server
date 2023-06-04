package org.wrang.reggie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wrang.reggie.common.R;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;
    /**
     * file参数名必须与前端name="file"，name值保持一致
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 文件名还可以采用UUID防止两个文件名重复会覆盖
        String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
        // 判断目录是否存在，不存在则创建
        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            // 一定要做保存在一个路径下，不然temp临时文件（默认放在tomcat里，.tmp换成.jpg就成打开图片）在请求结束后就会消失，
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        log.info("我执行了");
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(basePath + name));
            outputStream = response.getOutputStream();
            // "image/png"和"image/jpeg"都可以兼容png和jpg
            response.setContentType("image/png");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        }
    }
}
