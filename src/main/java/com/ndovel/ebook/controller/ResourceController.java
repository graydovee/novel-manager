package com.ndovel.ebook.controller;

import com.ndovel.ebook.config.SpiderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
public class ResourceController {
    private SpiderProperties spiderProperties;

    public ResourceController(SpiderProperties spiderProperties) {
        this.spiderProperties = spiderProperties;
    }

    @RequestMapping("/cover/{id}")
    public void getCover(HttpServletResponse response,@PathVariable String id) {

        response.setContentType("image/jpg");
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
}
