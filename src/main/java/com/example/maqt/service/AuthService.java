package com.example.maqt.service;

import com.example.maqt.entity.User;
import com.example.maqt.entity.enums.SystemRoleName;
import com.example.maqt.payload.ApiResponse;
import com.example.maqt.payload.RegisterDto;
import com.example.maqt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail()))
            return new ApiResponse("Bunday user mavjud",false);
        User user = new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(
                registerDto.getPassword())
        );
        userRepository.save(user);
        return new ApiResponse("User saqlandi",true) ;
    }
    public ApiResponse verifyEmail(String email, String emailcode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (emailcode.equals(user.getEmailCode())){
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("Acount aktivlashtirildi",true);
            }
            return new ApiResponse("Email yoki parol xato",false);
        }
        return new ApiResponse("bunday user mavjud emas",false);
    }
}
