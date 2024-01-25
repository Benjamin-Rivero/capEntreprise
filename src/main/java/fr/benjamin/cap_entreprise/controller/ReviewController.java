package fr.benjamin.cap_entreprise.controller;

import fr.benjamin.cap_entreprise.DTO.ReviewDTO;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.User;
import fr.benjamin.cap_entreprise.mapping.UrlRoute;
import fr.benjamin.cap_entreprise.service.GameService;
import fr.benjamin.cap_entreprise.service.ReviewService;
import fr.benjamin.cap_entreprise.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping(name="AppReview")
public class ReviewController {

    private final ReviewService reviewService;
    private final GameService gameService;
    private final UserService userService;

    @GetMapping(path=UrlRoute.URL_REVIEW,name = "list")
    public ModelAndView list(
            ModelAndView mav,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        mav.setViewName("review/index");
        mav.addObject("reviews",reviewService.findAll(pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_SHOW,name = "show")
    public ModelAndView show(
            @PathVariable Long id,
            ModelAndView mav
    ){
        mav.setViewName("review/show");
        mav.addObject("review",reviewService.findById(id));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            Principal principal
    ) {
        ReviewDTO reviewDTO = new ReviewDTO();
        if(principal!=null){

            User user = userService.findByUsername(principal.getName());
            reviewDTO.setPlayerId(user.getId());
        }
        return getFormByDTO(
                mav,
                reviewDTO,
                httpServletRequest.getRequestURI()
        );
    }

    @PostMapping(path = UrlRoute.URL_REVIEW_NEW, name = "newHandler")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, reviewDTO);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            ReviewDTO dto,
            String uri
    ) {
        mav.setViewName("review/form");
        mav.addObject("review", dto);
        mav.addObject("action", uri);
        mav.addObject("games",gameService.findAll());
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            ReviewDTO dto
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return mav;
        }
        reviewService.persist(dto);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_VALIDATE)
    public ModelAndView validate(
            @PathVariable Long id,
            ModelAndView mav,
            Principal principal
    ){
        Moderator moderator = (Moderator) userService.findByUsername(principal.getName());
        System.out.println(moderator.getId());
        this.reviewService.validate(id, moderator.getId());
        mav.setViewName("redirect:/avis");
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_REFUSE)
    public ModelAndView refuse(
            @PathVariable Long id,
            ModelAndView mav
    ){
        reviewService.refuse(id);
        mav.setViewName("redirect:/avis");
        return mav;
    }


}
