package com.example.maqt.controller;

import com.example.maqt.entity.User;
import com.example.maqt.payload.ApiResponse;
import com.example.maqt.payload.LoginDto;
import com.example.maqt.payload.RegisterDto;
import com.example.maqt.security.JwtProvider;
import com.example.maqt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
            ApiResponse apiResponse = authService.registerUser(registerDto);
            return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        try{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));

        User user = (User)authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("Parol yoki login hatoo",false));
    }
}

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email,@RequestParam String emailcode){
        ApiResponse apiResponse = authService.verifyEmail(email, emailcode);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
