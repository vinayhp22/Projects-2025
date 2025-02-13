package com.skyllx.expense.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("loggedUser") != null);
        boolean loginRequest = req.getRequestURI().equals("/login");

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/login");
        }
    }
}
