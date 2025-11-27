package com.sidalifetoumi.shorter.config.scurity;

/*
    @Author Fetoumi Sidali
    @Created 11/27/2025
*/

import com.sidalifetoumi.shorter.config.scurity.dto.AuthRequest;
import com.sidalifetoumi.shorter.config.scurity.dto.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationProvider authenticationProvider, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse login(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(),
                        authRequest.password()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());

        String token = jwtService.createToken(userDetails);

        return new AuthResponse(token);
    }
}
