package com.scaffolding.test.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.test.models.Utilisateur;
import com.scaffolding.test.repository.UtilisateurRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public Utilisateur login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndMotDePasse(loginRequest.loginName, loginRequest.loginPass);

        if (utilisateur != null) {
            // Getting the session
            HttpSession session = request.getSession();

            session.setAttribute("user", utilisateur);
        }

        return utilisateur;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }
}
