package com.ndovel.ebook;

import com.ndovel.ebook.repository.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ndovel.ebook.repository", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
