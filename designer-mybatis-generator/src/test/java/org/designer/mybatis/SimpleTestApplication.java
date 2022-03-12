package org.designer.mybatis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@MapperScan(basePackages = "org.designer", annotationClass = Mapper.class)
@SpringBootApplication(scanBasePackages = "org.designer")
public class SimpleTestApplication {

    @Bean
    @ConditionalOnMissingBean(value = ObjectMapper.class)
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }


}
