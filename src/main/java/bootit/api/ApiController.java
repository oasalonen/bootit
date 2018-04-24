package bootit.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Value("${POD_NAME}")
    private String podName;

    @Value("${app.build}")
    private Integer build;

    @RequestMapping("/api")
    public String api() {
        log.debug("API was called");
        return "API v" + build + " @ " + podName;
    }

    @RequestMapping("/nuke")
    public void nuke() {
        log.debug("Nuke called");
        System.exit(1);
    }

    @Cacheable(value = "cache-endpoint", key = "#seed")
    @RequestMapping("/cache/{seed}")
    public long cache(@PathVariable int seed) throws InterruptedException {
        log.debug("Cache miss for " + seed);
        Thread.sleep(5000);
        return System.currentTimeMillis();
    }
}
