package com.example.demo;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
//	@RequestMapping(value = "/hello")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(KeycloakPrincipal<KeycloakSecurityContext> principal, HttpSession ses, ModelAndView mav, Locale locale) {
        mav.setViewName("hello");
        AccessToken accessToken = principal.getKeycloakSecurityContext().getToken();
        mav.addObject("name", accessToken.getFamilyName() + accessToken.getGivenName());
        mav.addObject("message", "Hello! Spring Boot and Keycloak!");
        return mav;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
       request.logout();
       return "redirect:/";
    }
    
}
