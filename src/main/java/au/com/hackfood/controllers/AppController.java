package au.com.hackfood.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @RequestMapping(value = {"/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "HackFood Main Page");
        model.addObject("message", "Welcome to HackFood !");
        model.setViewName("home");
        return model;

    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView aboutPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("about");
        return model;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        } else if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
//        else {
//            model.addObject("msg", "Session Timed Out !!!");
//        }
        model.setViewName("login");

        return model;

    }

}