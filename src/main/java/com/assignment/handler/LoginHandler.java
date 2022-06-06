package com.assignment.handler;

import com.assignment.model.LoginModel;
import com.assignment.model.LoginResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginHandler {

    private final AuthenticationManager authenticationManager;
    private final CsrfTokenRepository csrfTokenRepository;

    public LoginHandler(AuthenticationManager authenticationManager, CsrfTokenRepository csrfTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.csrfTokenRepository = csrfTokenRepository;
    }

    public ResponseEntity<LoginResponse> login(LoginModel loginModel, HttpServletRequest httpServletRequest) {
        Authentication authentication = authenticationManager.authenticate(createAuthentication(loginModel));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        generateToken(httpServletRequest);
        return ResponseEntity.ok(toResponse(authentication));
    }

    private LoginResponse toResponse(Authentication authentication) {
        return LoginResponse.builder()
                .email(authentication.getName())
                .roles(toRoles(authentication))
                .build();
    }

    private List<String> toRoles(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private void generateToken(HttpServletRequest httpServletRequest) {
            invalidateSession(httpServletRequest);
            httpServletRequest.getSession(true);
            csrfTokenRepository.saveToken(csrfTokenRepository.generateToken(httpServletRequest), httpServletRequest, null);
    }

    private void invalidateSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null && !session.isNew())
            session.invalidate();
    }

    private UsernamePasswordAuthenticationToken createAuthentication(LoginModel loginModel) {
        return new UsernamePasswordAuthenticationToken(StringUtils.lowerCase(loginModel.getEmail()), loginModel.getPassword());
    }
}
