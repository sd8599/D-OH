package com.DOH.DOH.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {
    //메인 로그인 페이지 로드
    @GetMapping("/loginMain")
    public String showLoginMainPage() {

        return "user/loginMain";
    }
    @GetMapping("/login")
    public String showLoginPage() {

        return "user/login";
    }

}
