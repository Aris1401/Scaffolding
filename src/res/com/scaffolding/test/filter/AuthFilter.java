package com.scaffolding.test.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.scaffolding.test.models.Utilisateur;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter extends OncePerRequestFilter{
    static final List<String> UNFILTERED_URI = Arrays.asList(new String[] {"/auth"});

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String currentURI = request.getRequestURI();

        // Getting the session
        HttpSession session = request.getSession(false);

        // Getting the current user if exists
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("user") : null;
        if (user != null) {
            filterChain.doFilter(request, response);

            return;
        }

        // Checking if the current path can be passed through
        if(checkURIIfPassThrough(currentURI)) {
            filterChain.doFilter(request, response);

            return;
        }

        // Sinon envoyer une erreur non autoriser
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous avez besoin de vous connecter");
    }

    boolean checkURIIfPassThrough(String currentURI) {
        return UNFILTERED_URI.stream().anyMatch(uri -> currentURI.toLowerCase().contains(uri.toLowerCase()));
    }
}
