package com.example.auth_service.config;

import com.example.auth_service.entity.User;
import com.example.auth_service.enums.Role;
import com.example.auth_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring", value = "datasource.driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
          if(userRepository.findByUsername("admin").isEmpty())  {
              Set<String> roles = new HashSet<>();
              roles.add(Role.ADMIN.name());
              User user = User.builder().username("admin").password(passwordEncoder.encode("admin")).build();
              userRepository.save(user);
          }
        };
    }
}
