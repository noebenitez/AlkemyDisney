package com.example.challengeAlkemy.controllers;

import com.example.challengeAlkemy.exceptions.EmailAlreadyRegistered;
import com.example.challengeAlkemy.exceptions.GenerateTokenFailedException;
import com.example.challengeAlkemy.exceptions.UsernameAlreadyExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.User;
import com.example.challengeAlkemy.models.dto.LoginRequestDto;
import com.example.challengeAlkemy.models.dto.LoginResponseDto;
import com.example.challengeAlkemy.services.UserService;
import com.example.challengeAlkemy.utils.EntityURLBuilder;
import com.example.challengeAlkemy.utils.ResponseEntityMaker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.challengeAlkemy.utils.Constants.JWT_SECRET;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final String USERS_PATH = "/api/auth";
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper){
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) throws EmailAlreadyRegistered, IOException, UsernameAlreadyExistsException {
        User newUser = userService.register(user);
        sendRegisteredEmail(newUser.getEmail());
        return ResponseEntity.created(EntityURLBuilder.buildURL(USERS_PATH, newUser.getId())).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws GenerateTokenFailedException {

        User user = userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (user != null)
            return ResponseEntity.ok(LoginResponseDto.builder().token(this.generateToken(user)).build());
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String generateToken(User user) throws GenerateTokenFailedException {
        try{
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("DEFAULT_USER");
            String token = Jwts
                    .builder()
                    .setId("JWT")
                    .setSubject(user.getUsername())
                    .claim("user", objectMapper.writeValueAsString(user))
                    .claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000000))
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();
            return  token;
        } catch(Exception e) {
            throw new GenerateTokenFailedException();
        }
    }

    private void sendRegisteredEmail(String destination) throws IOException {
        Email from = new Email("ejbnbb@gmail.com");
        String subject = "Welcome to AlkemyDisney App";
        Email to = new Email(destination);
        Content content = new Content("text/plain", "Welcome! Now you are registered in Alkemy Disney App.");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            //logger.info(response.getBody());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
