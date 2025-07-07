package com.sao.galleria.config;

import com.sao.galleria.exception.BaseException;
import com.sao.galleria.exception.ErrorMessage;
import com.sao.galleria.exception.MessageType;
import com.sao.galleria.model.User;
import com.sao.galleria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    /**
     * Kullanıcı adı veritabanında arar ve kullanıcı bilgilerini UserDetails olarak döner.
     * Eğer kullanıcı bulunamazsa, BaseException fırlatır.
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> optional = userRepository.findByUsername(username);
                if (optional.isEmpty()) {
                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
                }
                return optional.get();
            }
        };
    }

    /**
     * BCryptPasswordEncoder, şifreleri güvenli bir şekilde şifrelemek için kullanılır.
     * Bu metod, BCryptPasswordEncoder nesnesini döner.
     *
     * @return bcryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * AuthenticationManager, kimlik doğrulama işlemlerini yönetir.
     * Bu metod, AuthenticationConfiguration'dan AuthenticationManager nesnesini döner.
     * Bu, Spring Security'nin kimlik doğrulama işlemlerini gerçekleştirmesine olanak tanır.
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * DaoAuthenticationProvider, kullanıcı kimlik doğrulama işlemlerini gerçekleştirmek için kullanılır.
     * Bu işlem, UserDetailsService ve PasswordEncoder kullanılarak yapılır.
     * İşlem tamamlandığında, kullanıcı bilgileri doğrulanır ve kimlik doğrulama işlemi başarılı olursa, kullanıcıya erişim izni verilir.
     * @return provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService()); //UserDetailsService, kullanıcı bilgilerini yüklemek için kullanılır.
        provider.setPasswordEncoder(passwordEncoder()); //PasswordEncoder, set edildi.
        return provider;
    }
}
