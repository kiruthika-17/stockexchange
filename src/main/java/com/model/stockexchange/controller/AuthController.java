package com.model.stockexchange.controller;

import com.model.stockexchange.dto.AuthenticationRequest;
import com.model.stockexchange.dto.AuthenticationResponse;
import com.model.stockexchange.entity.UserDetailsEntity;
import com.model.stockexchange.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    // This endpoint will generate jwt token and it will save user in db
    @PostMapping("/register")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String token = getJWTToken(authenticationRequest.getUserName());
        UserDetailsEntity entity = userService.registerUser(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(entity.getId(), authenticationRequest.getUserName(), token));

    }

    // This endpoint is used to do login, it will require jwt token in header
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDetailsEntity entity = userService.loginUser(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(entity.getId(), authenticationRequest.getUserName(), null));

    }


    private String getJWTToken(String userName) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWTId")
                .setSubject(userName)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
