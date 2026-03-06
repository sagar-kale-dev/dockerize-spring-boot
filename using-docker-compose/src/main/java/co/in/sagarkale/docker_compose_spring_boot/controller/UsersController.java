package co.in.sagarkale.docker_compose_spring_boot.controller;

import co.in.sagarkale.docker_compose_spring_boot.dto.UserDto;
import co.in.sagarkale.docker_compose_spring_boot.dto.UserReqDto;
import co.in.sagarkale.docker_compose_spring_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserReqDto userReqDto){
        UserDto userDto = userService.createUser(userReqDto);
        return ResponseEntity.ok(userDto);
    }
}
