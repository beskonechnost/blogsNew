package com.blogs.audit;

import com.blogs.entity.AuditEntity;
import com.blogs.service.AuditService;
import com.blogs.utils.UserIP;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Transactional
@Log4j
@Component
public class AuthenticationAuditor extends HttpStatusReturningLogoutSuccessHandler implements ApplicationListener<AbstractAuthenticationEvent> {

    private static final String LOGIN_ACTION = "LOGIN";
    private static final String LOGOUT_ACTION = "LOGOUT";

    private final AuditService auditService;

    @Autowired
    public AuthenticationAuditor (AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        try {
            if(event instanceof AuthenticationSuccessEvent) {
                AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
                Authentication authentication = authEvent.getAuthentication();
                AuditEntity authenticationAuditEntity = new AuditEntity(LOGIN_ACTION, authentication.getName(), true);
                authenticationAuditEntity.setIpAddress(UserIP.getUserIP());
                auditService.save(authenticationAuditEntity);
            }

            if(event instanceof AbstractAuthenticationFailureEvent) {
                AbstractAuthenticationFailureEvent authEvent = (AbstractAuthenticationFailureEvent) event;
                Authentication authentication = authEvent.getAuthentication();
                AuditEntity authenticationAuditEntity = new AuditEntity(LOGIN_ACTION, authentication.getName(), false);
                authenticationAuditEntity.setDescription(authEvent.getException().getMessage());
                authenticationAuditEntity.setIpAddress(UserIP.getUserIP());
                auditService.save(authenticationAuditEntity);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void onLogoutSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuditEntity authenticationAuditEntity = new AuditEntity(LOGOUT_ACTION, authentication.getName(), true);
        authenticationAuditEntity.setIpAddress(UserIP.getUserIP());
        auditService.save(authenticationAuditEntity);
        super.onLogoutSuccess(request, response, authentication);
    }
}
