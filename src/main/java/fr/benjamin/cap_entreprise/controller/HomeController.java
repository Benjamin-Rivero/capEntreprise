package fr.benjamin.cap_entreprise.controller;

import fr.benjamin.cap_entreprise.entity.User;
import fr.benjamin.cap_entreprise.mapping.UrlRoute;
import fr.benjamin.cap_entreprise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal
    ) {
        if(principal == null){
            System.out.println("Pourquoi tu fais ca ?");
            mav.setViewName("redirect:"+UrlRoute.URL_LOGIN);
            return mav;
        }
        System.out.println("Tu devrais pas être la toi");
        User user = userService.findByUsername(principal.getName());
        System.out.println("Connected user : "+user.getUsername());
        mav.setViewName("redirect:/avis");
        return mav;
    }

}
