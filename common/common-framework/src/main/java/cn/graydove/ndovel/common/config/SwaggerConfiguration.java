package cn.graydove.ndovel.common.config;

import cn.graydove.ndovel.common.properties.NovelProperties;
import cn.graydove.ndovel.common.properties.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;

/**
 * @author graydove
 */
@Configuration
@ConditionalOnProperty(prefix = "novel.swagger", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(NovelProperties.class)
public class SwaggerConfiguration {

    private NovelProperties novelProperties;

    public SwaggerConfiguration(NovelProperties novelProperties) {
        this.novelProperties = novelProperties;
    }

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket docket() {
        SwaggerProperties swagger = novelProperties.getSwagger();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        Optional.ofNullable(swagger.getPathMapping()).ifPresent(docket::pathMapping);
        return docket;
    }

    private ApiInfo apiInfo(SwaggerProperties swagger) {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        Optional.ofNullable(novelProperties.getVersion()).ifPresent(apiInfoBuilder::version);
        Optional.ofNullable(swagger.getTitle()).ifPresent(apiInfoBuilder::title);
        Optional.ofNullable(swagger.getDescription()).ifPresent(apiInfoBuilder::description);
        apiInfoBuilder.contact(new Contact(swagger.getContactName(), swagger.getContactUrl(), swagger.getContactEmail()));
        return apiInfoBuilder.build();
    }
}
