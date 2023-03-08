package com.zzh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){//Environment environment

//        Profiles profiles = Profiles.of("div","test");
//
//        boolean b = environment.acceptsProfiles(profiles);
//          默认访问地址：http://localhost:8080/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .build();
//                .apis(RequestHandlerSelectors.basePackage("com.zzh.web.controller")) 扫描特定的包
//                .paths() 扫描特定的路径

    }

    private ApiInfo apiInfo(){

        Contact contact = new Contact("朱子豪", "https://www.baidu.com", "906440945@qq.com");

        return new ApiInfo("朱子豪的Swagger文档日志",
                "什么时候能给自己new个对象啊！",
                "v1.0",
                "https://blog.csdn.net/catoop/article/details/50668896",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
