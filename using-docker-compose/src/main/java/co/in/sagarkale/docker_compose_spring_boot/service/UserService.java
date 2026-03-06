package co.in.sagarkale.docker_compose_spring_boot.service;

import co.in.sagarkale.docker_compose_spring_boot.dto.UserDto;
import co.in.sagarkale.docker_compose_spring_boot.dto.UserReqDto;
import co.in.sagarkale.docker_compose_spring_boot.entity.User;
import co.in.sagarkale.docker_compose_spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> getAllUser(){
        List<User> users = userRepository.findAll();

        if(!users.isEmpty()){
            return users.stream().map((element) -> modelMapper.map(element, UserDto.class)).toList();
        }

        return new ArrayList<>();

    }

    public UserDto createUser(UserReqDto userReqDto){
        User user = userRepository.save(modelMapper.map(userReqDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }
}
