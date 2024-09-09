package com.DOH.DOH.controller.list;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping("main")
    public String main(){
        return "list/main";
    }
}
