package gc.templates.java.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Gennadii Cherniaiev
 * Date: 5/22/2015
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("hello");
    }
}
