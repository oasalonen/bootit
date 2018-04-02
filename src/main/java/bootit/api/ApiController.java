package bootit.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Value("${POD_NAME}")
    private String podName;

    @RequestMapping("/api")
    public String api() {
        log.debug("API was called");
        return "APIv10 @ " + podName;
    }

    @RequestMapping("/nuke")
    public void nuke() {
        log.debug("Nuke called");
        System.exit(1);
    }
}
