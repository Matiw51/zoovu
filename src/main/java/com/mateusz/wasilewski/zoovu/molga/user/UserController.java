package com.mateusz.wasilewski.zoovu.molga.user;

import com.mateusz.wasilewski.zoovu.molga.user.User;
import com.mateusz.wasilewski.zoovu.molga.user.UserDto;
import com.mateusz.wasilewski.zoovu.molga.user.UserMapper;
import com.mateusz.wasilewski.zoovu.molga.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/leaderboard")
    public List<UserDto> getLeaderboard() {
        List<User> users = userRepository.findAll();
        users.sort(Comparator.comparingInt(User::getScore).reversed());
        return users.stream().map(x -> userMapper.toUserDto(x)).collect(Collectors.toList());
    }

    @GetMapping("/leaderboard/{date}")
    public List<UserDto> getLeaderboardSinceDate(@PathVariable("date") @DateTimeFormat(pattern = "ddMMyyyy") String dateAsString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = formatter.parse(dateAsString);
        List<User> usersSinceDate = userRepository.findAll().stream().filter(x -> x.getScoringDate().after(date)).collect(Collectors.toList());
        usersSinceDate.sort(Comparator.comparingInt(User::getScore).reversed());
        return usersSinceDate.stream().map(x -> userMapper.toUserDto(x)).collect(Collectors.toList());
    }
}
