package com.sao.service.impl;

import com.sao.jwt.AuthRequest;
import com.sao.jwt.AuthResponse;
import com.sao.jwt.JwtService;
import com.sao.model.dto.UserDto;
import com.sao.model.entity.User;
import com.sao.repository.UserRepository;
import com.sao.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Şub 2025
 * <p>
 * @description:
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Bu encoderin AppConfig altında bean'i oluşturuldu. Kullanıcının şifresini kriptolamak için kullanılıyor.
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDto register(AuthRequest request) {
        UserDto userDto = new UserDto();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));/** Kullanıcıdan alınan password kriptolanıp o şekilde set edildi.*/
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            System.out.println("Kullanıcı adı sistemde kayıtlı");
            return null;
        }

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, userDto);

        return userDto;
    }

    /**
     * AppConfig içinde tanımlanan AuthenticationProvider kullanıcı adı ve şifre doğrulamasını yapacak
     * @param request
     * @return
     */
    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(authentication);

            /** Kimlik doğrulama başarılı ise kullanıcı adına göre kullanıcıyı veritabanından bul*/
            Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

            /** generateToken metodu bizden UserDetails sınıfından nesne ister. User sınıfı UserDetails'i extend ettiği için yerine kullandık.*/
            String token = jwtService.generateToken(optionalUser.get());
            return new AuthResponse(token);
        } catch (Exception e) {
            System.out.println("Kullanıcı adı veya şifre hatalı " + e.getMessage());
        }
        return null;
    }
}
