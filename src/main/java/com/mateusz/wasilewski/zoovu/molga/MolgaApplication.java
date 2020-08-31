package com.mateusz.wasilewski.zoovu.molga;

import com.mateusz.wasilewski.zoovu.molga.authority.Authority;
import com.mateusz.wasilewski.zoovu.molga.authority.AuthorityRepository;
import com.mateusz.wasilewski.zoovu.molga.user.User;
import com.mateusz.wasilewski.zoovu.molga.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class MolgaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MolgaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, AuthorityRepository authorityRepository) {
        Authority authority = new Authority();
        authority.setRole("ADMIN");
        authorityRepository.save(authority);
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach((name -> {
                User user = new User();
                user.setAuthorities(Collections.singleton(authority));
                user.setUsername(name);
                user.setPassword(name);
                user.setScore((int)(Math.random()*100));
                user.setScoringDate(new Date());
                userRepository.save(user);
            }));
            userRepository.findAll().forEach(System.out::println);
        };
    }

}
