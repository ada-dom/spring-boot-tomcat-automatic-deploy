package com.switchfully.spring.springboottomcatautomaticdeploy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    @RequestMapping("/")
    @ResponseBody
    String getWelcomeMessage() {
        return "Hello World!";
    }
}
