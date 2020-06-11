package com.ndovel.novel.controller;

import com.ndovel.novel.config.AppProperties;
import com.ndovel.novel.config.SpiderProperties;
import com.ndovel.novel.model.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
public class ResourceController {
    private SpiderProperties spiderProperties;
    private AppProperties appProperties;

    public ResourceController(SpiderProperties spiderProperties, AppProperties appProperties) {
        this.spiderProperties = spiderProperties;
        this.appProperties = appProperties;
    }

    @RequestMapping("/cover/{id}")
    public void getCover(HttpServletResponse response,@PathVariable String id) {

        File imageFile = new File(spiderProperties.getCoverPath(), id+ ".jpg");
        if (imageFile.exists()){
            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(imageFile));

                response.setContentType("image/jpeg");
                OutputStream out = response.getOutputStream();

                out.write(inputStream.readAllBytes());
                out.flush();
            } catch (IOException e) {
                log.error("IO异常");
            }
        }

    }

    @RequestMapping("/download")
    public String download(){
        if (appProperties.getDownLoadUrl() != null) {
            String URL = appProperties.getDownLoadUrl();
            if (!URL.endsWith("/")) {
                URL += "/";
            }
            URL += appProperties.getAppVersion() + ".apk";
            return "redirect:" + URL;
        } else {
            return "redirect:download_native";
        }
    }

    @RequestMapping("/download_native")
    public void downloadNative(HttpServletResponse response){
        File appFile = new File(appProperties.getPath(), appProperties.getAppVersion() + ".apk");
        if (appFile.exists()){
            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(appFile));
                response.setContentType("bin");
                response.addHeader("Content-Disposition", "attachment; filename=ndovel.apk");
                OutputStream out = response.getOutputStream();
                out.write(inputStream.readAllBytes());
                out.flush();
            } catch (IOException e) {
                log.error("IO异常");
            }
        }

    }

    @ResponseBody
    @RequestMapping("/version")
    public Response version(){
        return Response.success(appProperties.getAppVersion());
    }
}
