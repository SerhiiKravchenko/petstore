package io.swagger.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.chtrembl.petstore.product.repository")
@EntityScan("com.chtrembl.petstore.product.model")
public class PersistenceConfig {
}
