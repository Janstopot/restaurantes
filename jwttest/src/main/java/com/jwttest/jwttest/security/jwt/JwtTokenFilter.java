package com.jwttest.jwttest.security.jwt;


import com.jwttest.jwttest.security.entity.User;
import com.jwttest.jwttest.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(req);
        System.out.println("tokennnnnn = " + token);
        try{


            if(token != null && jwtProvider.validateToken(token)){
                System.out.println("LLEGAMOS AQUI??????");
                String name = jwtProvider.getEmailFromToken(token);
                System.out.println("EMAAAAAILLL" + name);
                //System.out.println("PILLANDO EL USER: " + userDetailsService.getUserByEmail(name));
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(name);
                //User user = userDetailsService.getUserByEmail(name);
                System.out.println("USER DETAILS ");
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println("USERNMAPASSOWDODSALSKD" );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (Exception e){
            logger.error("doFilter method fail " + e);

        }
        filterChain.doFilter(req, res);

    }


    public String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer", "");
        return null;
    }
}
