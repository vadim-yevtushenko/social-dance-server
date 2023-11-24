package com.example.socialdanceserver.config.orika;

import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import lombok.extern.java.Log;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class OrikaConfiguration implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {

        log.info("Orika converters registered");
    }
}
