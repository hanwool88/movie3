package moviereservation.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${echo.log.level}")
	String echoLogLevel;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hi MSA Movie World!" + " " + echoLogLevel;

	}
}
