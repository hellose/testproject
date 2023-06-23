package study.testproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DefaultController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/page/{pageName}")
	public String page(@PathVariable(name = "pageName") String pageName) {
		log.debug("{}", ("page/" + pageName));
		return "page/" + pageName;
	}

	@GetMapping("/vuejs/{pageName}")
	public String vuejs(@PathVariable(name = "pageName") String pageName) {
		return "page/vuejs/" + pageName;
	}

	@GetMapping("/bootstrap/{pageName}")
	public String bootstrap(@PathVariable(name = "pageName") String pageName) {
		return "page/bootstrap/" + pageName;
	}
}
