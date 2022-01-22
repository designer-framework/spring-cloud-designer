package org.designer.function.configuration;

import org.springframework.cloud.deployer.resource.maven.MavenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/15 0:03
 */
@Configuration
public class FunctionConfig {
    @Bean
    public MavenProperties localMavenProperties() {
        MavenProperties properties = new MavenProperties();
        properties.setLocalRepository("D:\\D_MAVEN");
        return properties;
    }

}
