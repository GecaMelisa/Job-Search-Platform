package config;

import ba.edu.ibu.job.search.platform.core.service.JwtService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtServiceConfig {
    @Bean
    public JwtService jwtService() {
        return Mockito.mock(JwtService.class);
    }
}
