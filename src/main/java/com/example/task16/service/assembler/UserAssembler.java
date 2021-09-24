package com.example.task16.service.assembler;


import com.example.task16.db.entity.Profile;
import com.example.task16.db.entity.Role;
import com.example.task16.db.entity.User;
import com.example.task16.service.dto.ProfileDto;
import com.example.task16.service.dto.RoleDto;
import com.example.task16.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserAssembler implements Assembler<User, UserDto> {
    @Override
    public UserDto mergeAggregateIntoDto(User entity) {
       UserDto userDto = new UserDto();
       userDto.setProfile(mergeAggregateIntoDto(entity.getProfile()));
       userDto.setUserEmail(entity.getUserEmail());
       userDto.setPassword(entity.getPassword());
       userDto.setLogin(entity.getLogin());
       userDto.setUserId(entity.getUserId());
       userDto.setRoles(entity.getRoles().stream().map(this::mergeAggregateIntoDto).collect(Collectors.toSet()));
       return userDto;
    }

    @Override
    public User mergeDtoIntoAggregate(UserDto dto) {
        User user = new User();
        Profile profile = mergeDtoIntoAggregate(dto.getProfile());
        profile.setUser(user);
        user.setProfile(profile);
        user.setUserId(dto.getUserId());
        user.setUserEmail(dto.getUserEmail());
        user.setPassword(dto.getPassword());
        user.setLogin(dto.getLogin());
        user.setRoles(dto.getRoles().stream().map(roleDto -> {
            Role role = mergeDtoIntoAggregate(roleDto);
            role.setUser(user);
            return role;
        }).collect(Collectors.toSet()));
        return user;
    }

    private ProfileDto mergeAggregateIntoDto(Profile entity) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileId(entity.getProfileId());
        profileDto.setUserSurName(entity.getUserSurName());
        profileDto.setUserFirstName(entity.getUserFirstName());
        profileDto.setAccountBalance(entity.getAccountBalance());
        profileDto.setUserRegistrationDate(entity.getUserRegistrationDate());
        profileDto.setUserId(entity.getUser().getUserId());
       return profileDto;

    }


    private Profile mergeDtoIntoAggregate(ProfileDto dto) {
        Profile profile = new Profile();
        profile.setProfileId(dto.getProfileId());
        profile.setAccountBalance(dto.getAccountBalance());
        profile.setUserFirstName(dto.getUserFirstName());
        profile.setUserSurName(dto.getUserSurName());
        profile.setUserRegistrationDate(dto.getUserRegistrationDate());
        return profile;
    }

    private RoleDto mergeAggregateIntoDto(Role entity) {
       RoleDto roleDto = new RoleDto();
       roleDto.setUserAuthority(entity.getUserAuthority());
       roleDto.setId(entity.getId());
       roleDto.setUserId(entity.getUser().getUserId());
return roleDto;
    }


    private Role mergeDtoIntoAggregate(RoleDto dto) {
       Role role = new Role();
       role.setUserAuthority(dto.getUserAuthority());
       role.setId(dto.getId());
       return role;
    }
}
