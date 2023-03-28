package techsuppDev.techsupp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    private String resourcePath = "/upload/**"; // view 에서 접근할 경로
//    private String savePath = "/Users/leesoyoung/Desktop/Funding/techsupp/mytechsupp/src/main/resources/static/file/"; // 실제파일경로
    @Value("${uploadPath}")
    String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(resourcePath)
//                .addResourceLocations(savePath);
                registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadPath);

    }
}
