package nl.hu.bep3.jobboard.candidates.storage;

import nl.hu.bep3.jobboard.candidates.core.JobRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HttpJobRepository implements JobRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpJobRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public List<UUID> findJobsByKeyword(String keyword) {
        URI uri = URI.create(this.rootPath + "/jobs?keyword=" + keyword);
        JobResult[] results = this.client.getForObject(uri, JobResult[].class);

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> result.id)
                .collect(Collectors.toList());
    }
}
