package fr.benjamin.cap_entreprise.controller;


import fr.benjamin.cap_entreprise.DTO.GameDTO;
import fr.benjamin.cap_entreprise.entity.Game;
import fr.benjamin.cap_entreprise.service.FileUploadService;
import fr.benjamin.cap_entreprise.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    private final GameService gameService;

    @GetMapping("/jeu/{id}/upload")
    public ModelAndView uploadPage(
            @PathVariable Long id,
            ModelAndView mav
    ){
        mav.addObject("id",id);
        mav.setViewName("file_upload/file_upload");
        return mav;
    }

    @PostMapping("/jeu/{id}/upload/game_image")
    public ModelAndView fileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file, ModelAndView mav) {
        GameDTO gameDTO = gameService.getDTOById(id);
        gameDTO.setImage(fileUploadService.uploadFile(file, "jeu"));
        gameService.persist(gameDTO,id);
        mav.setViewName("redirect:/jeu");
        return mav;
    }

}
