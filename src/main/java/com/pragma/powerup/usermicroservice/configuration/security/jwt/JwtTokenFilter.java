package com.pragma.powerup.usermicroservice.configuration.security.jwt;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailsServiceImpl;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private List<String> excludedPrefixes = Arrays.asList("/auth/**", "/swagger-ui/**", "/user");
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(req);
        if (token != null && jwtProvider.validateToken(token)) {
            String mail = jwtProvider.getMailFromToken(token);
            String userRole = jwtProvider.getRoleFromToken(token);

            if (isCreateUserRequest(req)) {
                String userType = extractUserTypeFromRequest(req);
                if (userType.equalsIgnoreCase(Constants.OWNER_NAME)) {
                    if (!userRole.equals(Constants.ROLE_ADMIN_NAME)) {
                        throw new UnauthorizedException();
                    }
                } else if (userType.equalsIgnoreCase(Constants.EMPLOYEE_NAME)) {
                    if (!userRole.equalsIgnoreCase(Constants.ROLE_OWNER_NAME)) {
                        throw new UnauthorizedException();
                    }
                }
            }
            if (isDeleteUserRequest(req)) {
                String userType = extractUserTypeFromRequest(req);

                if (userType.equalsIgnoreCase(Constants.OWNER_NAME)) {
                    if (!userRole.equalsIgnoreCase(Constants.ROLE_ADMIN_NAME)) {
                        throw new UnauthorizedException();
                    }
                } else if (userType.equalsIgnoreCase(Constants.EMPLOYEE_NAME)) {
                    if (!userRole.equalsIgnoreCase(Constants.ROLE_OWNER_NAME)) {
                        throw new UnauthorizedException();
                    }
                }
            }
            if (isUpdateUserRequest(req)) {
                String userType = extractUserTypeFromRequest(req);

                if (userType.equalsIgnoreCase(Constants.OWNER_NAME)) {
                    if (!userRole.equalsIgnoreCase(Constants.ROLE_ADMIN_NAME)) {
                        throw new UnauthorizedException();
                    }
                } else if (userType.equalsIgnoreCase(Constants.EMPLOYEE_NAME)) {
                    if (!userRole.equalsIgnoreCase(Constants.ROLE_OWNER_NAME)) {
                        throw new UnauthorizedException();
                    }
                }
            }

            if (isGetUserRequest(req)) {
                if (!userRole.equalsIgnoreCase(Constants.ROLE_ADMIN_NAME) && !userRole.equalsIgnoreCase(Constants.ROLE_OWNER_NAME)) {
                    throw new UnauthorizedException();
                }
            } else {

            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(mail);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }

    private String extractUserTypeFromRequest(HttpServletRequest req) {
        return req.getParameter("userType");
    }

    private String extractRequestBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private boolean isCreateUserRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("POST")
                && request.getRequestURI().contains("/user/create");
    }
    private boolean isDeleteUserRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("DELETE")
                && request.getRequestURI().contains("/delete/{userId}");
    }

    private boolean isUpdateUserRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("PUT")
                && request.getRequestURI().contains("/update/{userId}");
    }
    private boolean isGetUserRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET")
                && request.getRequestURI().contains("/all");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String currentRoute = request.getServletPath();
        for (String prefix : excludedPrefixes) {
            if (pathMatcher.matchStart(prefix, currentRoute)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // return everything after "Bearer "
        }
        return null;
    }
}
