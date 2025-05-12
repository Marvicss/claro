package com.claro.claro.modules.auth.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionService {

    public void saveSession(Authentication auth) {
        try {

            SecurityContextHolder.getContext().setAuthentication(auth);


            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (requestAttributes == null) {
                throw new IllegalStateException("Não foi possível obter o contexto da requisição.");
            }

            HttpSession session = requestAttributes.getRequest().getSession(true);


            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch (Exception e) {

            throw new RuntimeException("Erro ao salvar sessão de autenticação: " + e.getMessage(), e);
        }
    }
}

