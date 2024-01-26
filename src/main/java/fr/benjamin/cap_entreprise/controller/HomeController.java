package fr.benjamin.cap_entreprise.controller;

import fr.benjamin.cap_entreprise.entity.User;
import fr.benjamin.cap_entreprise.mapping.UrlRoute;
import fr.benjamin.cap_entreprise.service.ExcelReviewService;
import fr.benjamin.cap_entreprise.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ExcelReviewService excelService;

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


    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response) {
        try {
            File file = excelService.writeExcel();
            ByteArrayInputStream excelToByte = new ByteArrayInputStream(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath()))
            );
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(excelToByte, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
