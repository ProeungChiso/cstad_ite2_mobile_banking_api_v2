package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.base.BasedMessage;
import co.istad.ite2_mbanking_api_v2.domain.Role;
import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserUpdateRequest;
import co.istad.ite2_mbanking_api_v2.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public void createNew(UserCreateRequest request) {

        if(userRepository.existsByPhoneNumber(request.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }
        if(userRepository.existsByStudentIdCard(request.studentIdCard())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }
        if(userRepository.existsByNationalCardId(request.nationalCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }
        if(!request.password().equals(request.confirmedPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(request);
        user.setUuid(UUID.randomUUID().toString());
        user.setProfileImage("image.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsDeleted(false);
        user.setIsBlocked(false);

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "role user has not been found!"));

        request.roles().forEach(r -> {
            Role newRole = roleRepository.findByName(r.name())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "role user has not been found!"));
            roles.add(newRole);
        });

        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        return userMapper.toUserResponse(user);
    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(userMapper::toUserResponse);
    }


    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest request) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        log.info("before user: {}", user);
        userMapper.fromUserUpdateRequest(request, user);
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);

    }
    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {
        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }
        userRepository.blockByUuid(uuid);
        return new BasedMessage("User has been blocked");
    }

    @Override
    public void deleteByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        userRepository.delete(user);

    }


}
