package com.example.application.security;

import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.shared.ApplicationConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public class SecurityUtils {

    private SecurityUtils() {}

    static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        final String parameter = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameter != null &&
        Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameter));
    }

    static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = false;
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            isLoggedIn = true;
        }
        return isLoggedIn;
    }
}
