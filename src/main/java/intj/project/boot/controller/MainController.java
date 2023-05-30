package intj.project.boot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class MainController {
    @GetMapping(value = "/index")
    public String home() {
        return "index";
    }
    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("uri", "login");
        return "index";
    }
}
