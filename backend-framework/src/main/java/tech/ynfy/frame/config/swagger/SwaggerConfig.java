package tech.ynfy.frame.config.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger: https://springdoc.org/
 * migrating-from-springfox: https://springdoc.org/index.html#migrating-from-springfox
 * open API v3: https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/2
 */
@Configuration
public class SwaggerConfig {

    @Value("${springdoc.info.version}")
    private String version;

    @Value("${springdoc.info.title}")
    private String title;

    @Value("${springdoc.info.desc}")
    private String desc;

    @Value("${springdoc.info.license}")
    private String license;

    @Value("${springdoc.info.url}")
    private String url;


    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .packagesToScan("tech.ynfy.system.swagger.controller")
                             .group("spring-public")
                             .addOpenApiMethodFilter(i -> i.isAnnotationPresent(Operation.class))
                             .build();
    }

/*        @Bean
        public GroupedOpenApi adminApi() {
            String paths = "";
            String packagedToMatch = "";
            return GroupedOpenApi.builder()
                                 // 分组名
                                 .group("springs-admin")
                                 // 路径匹配
                                 .pathsToMatch(paths)
                                 // 包名匹配
                                 .packagesToScan(packagedToMatch)
                                 // 过滤器
                                 .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
                                 .build();
        }*/


    /**
     * global header
     *
     * @return
     */
    @Bean
    public OpenAPI globalHeaderAPI() {
        return new OpenAPI().info(new Info().title(title)
                                            .description(desc)
                                            .version(version)
                                            .license(new License().name(license).url(url)));
    }

}
