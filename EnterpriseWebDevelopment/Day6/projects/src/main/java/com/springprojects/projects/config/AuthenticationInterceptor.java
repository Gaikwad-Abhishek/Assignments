package com.springprojects.projects.config;


import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springprojects.projects.customAnnotations.Authenticated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authenticated authenticatedAnnotation = handlerMethod.getMethodAnnotation(Authenticated.class);

            // If the method is annotated with @Authenticated, check for authentication
            if (authenticatedAnnotation != null) {
                Object userId = request.getSession().getAttribute("userId");
                if (userId == null) {
                    // User is not authenticated, deny access
                    response.sendRedirect("http://localhost:8080/cycles/login"); // Redirect to the login page
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // This method can be left empty
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // This method can be left empty
    }
}
