package cn.graydove.ndovel.spider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class);
    }
}
