package forum.main.controller;

import forum.main.dto.UserRequestDto;
import forum.main.dto.UserResponseDto;
import forum.main.entity.User;
import forum.main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponseDto getUser(@RequestParam long id){
        User user = userService.readUser(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserResponseDto(user);
    }

    @PostMapping
    public long createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto.getUsername(), userRequestDto.getKeywordList());
    }
}
