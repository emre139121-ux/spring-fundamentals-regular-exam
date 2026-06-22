package app.web;

import app.model.dto.user.UserDto;
import app.model.dto.user.UserLoginRequest;
import app.model.dto.user.UserRegisterRequest;
import app.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    private UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("userLoginRequest", userLoginRequest);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("userRegisterRequest", userRegisterRequest);

        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView registerUser(@Valid UserRegisterRequest userRegisterRequest,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("register");
            return modelAndView;
        }

        userService.register(userRegisterRequest);

        return new ModelAndView("redirect:/login");
    }
    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginRequest userLoginRequest,
                              BindingResult bindingResult,
                              HttpSession httpSession
    ) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }

        UserDto user = userService.login(userLoginRequest);
        httpSession.setAttribute("user_id", user.getId());

        return new ModelAndView("redirect:/home");
    }
    @PostMapping("/logout")
    public ModelAndView getLogoutPage(HttpSession httpSession) {
        httpSession.invalidate();
        return new ModelAndView("redirect:/");
    }
}
