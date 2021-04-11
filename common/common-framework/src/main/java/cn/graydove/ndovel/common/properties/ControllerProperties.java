package cn.graydove.ndovel.common.properties;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author graydove
 */
@Data
public class ControllerProperties {

    private List<String> ignoredControllerPackage = Arrays.asList(
            "springfox.documentation.swagger.web",
            "springfox.documentation.swagger2.web",
            "org.springframework.boot.autoconfigure.web"
    );

    private List<String> ignoredResponseBodyClass = Arrays.asList(
            "cn.graydove.ndovel.common.response.Response",
            "org.springframework.http.ResponseEntity"
    );

}
