package fr.kevin.exam.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("redirect:/login");
        return mav;
    }

}
