package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.base.BasedMessage;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest request){
        userService.createNew(request);
    }
    @GetMapping({"/{uuid}"})
    UserResponse findByUuid(@PathVariable String uuid){
        return userService.findByUuid(uuid);
    }
    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest request) {
        return userService.updateByUuid(uuid, request);
    }
    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/{uuid}/delete"})
    void deleteByUuid(@PathVariable String uuid){
        userService.deleteByUuid(uuid);
    }
    @GetMapping
    Page<UserResponse> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "5") int limit){
        return userService.findList(page, limit);
    }




}
