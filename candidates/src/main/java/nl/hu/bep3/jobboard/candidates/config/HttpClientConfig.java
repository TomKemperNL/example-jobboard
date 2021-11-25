package nl.hu.bep3.jobboard.candidates.config;

import nl.hu.bep3.jobboard.candidates.storage.HttpJobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.job}")
    private String rootPath;

    @Bean
    public HttpJobRepository httpJobRepository() {
        return new HttpJobRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
