package fr.benjamin.cap_entreprise.controller;

import fr.benjamin.cap_entreprise.DTO.GameDTO;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.User;
import fr.benjamin.cap_entreprise.mapping.UrlRoute;
import fr.benjamin.cap_entreprise.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name="AppGame")
public class GameController {

    private final GameService gameService;
    private final UserService userService;
    private final PublisherService publisherService;
    private final GenreService genreService;
    private final ClassificationService classificationService;
    private final PlatformService platformService;
    private final BusinessModelService businessModelService;

    @GetMapping(path= UrlRoute.URL_GAME,name = "list")
    public ModelAndView list(
            ModelAndView mav,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "publishedAt" }, // order by
                    direction = Sort.Direction.DESC
            )Pageable pageable
            ){
        gameService.test();
        mav.setViewName("game/index");
        mav.addObject("games",gameService.findAll(pageable));
        return mav;
    }

    @GetMapping(path=UrlRoute.URL_GAME_ID,name="show")
    public ModelAndView show(
            @PathVariable Long id,
            ModelAndView mav
    ){
        mav.addObject("game",gameService.findById(id));
        mav.setViewName("game/show");
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            Principal principal
    ) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setModeratorId(userService.findByUsername(principal.getName()).getId());

        if(principal!=null){
            User user = userService.findByUsername(principal.getName());
        }
        return getFormByDTO(
                mav,
                gameDTO,
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @PostMapping(path = UrlRoute.URL_GAME_NEW, name = "newHandler")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("game") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, gameDTO,null);
    }

    @GetMapping(path = UrlRoute.URL_GAME_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                gameService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_GAME_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("platform") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, gameDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            GameDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("game/form");
        mav.addObject("game", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        mav.addObject("publishers",publisherService.findAllSorted(Sort.by("name").ascending()));
        mav.addObject("genres",genreService.findAllSorted(Sort.by("name").ascending()));
        mav.addObject("classifications",classificationService.findAll());
        mav.addObject("platforms",platformService.findAllSorted(Sort.by("name").ascending()));
        mav.addObject("businessModels",businessModelService.findAllSorted(Sort.by("name").ascending()));
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            GameDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("game/form");
            mav.addObject("publishers",publisherService.findAll());
            mav.addObject("genres",genreService.findAll());
            mav.addObject("classifications",classificationService.findAll());
            mav.addObject("platforms",platformService.findAll());
            mav.addObject("businessModels",businessModelService.findAll());
            return mav;
        }
        gameService.persist(dto,id);
        mav.setViewName("redirect:" + UrlRoute.URL_GAME); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }

    @GetMapping(UrlRoute.URL_GAME_DELETE)
    public ModelAndView delete(
            @PathVariable Long id,
            ModelAndView mav
    ){
        this.gameService.delete(id);
        mav.setViewName("redirect:/jeu");
        return mav;
    }

}
