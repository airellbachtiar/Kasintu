package com.kasintu.services.loginservices.impl;

import com.kasintu.dtos.logindtos.AccessTokenDTO;
import com.kasintu.dtos.logindtos.LoginRequestDTO;
import com.kasintu.dtos.logindtos.LoginResponseDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.RoleEnum;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.loginservices.AccessTokenEncoder;
import com.kasintu.services.loginservices.LoginService;
import com.kasintu.services.loginservices.exception.InvalidLoginException;
import com.kasintu.services.userservices.impl.HashPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final AdminRepository adminRepository;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername());
        if(user == null)
        {
            throw new InvalidLoginException();
        }
        if(!matchPassword(request.getPassword(), user.getPassword()))
        {
            throw new InvalidLoginException("PASSWORD_INVALID");
        }

        String accessToken = generateAccessToken(user);
        return LoginResponseDTO.builder().accessToken(accessToken).build();
    }

    private boolean matchPassword(String insertedPassword, String realPassword)
    {
        String hashedInsertedPassword = HashPassword.hashPassword(insertedPassword);
        return hashedInsertedPassword.equals(realPassword);
    }

    private String generateAccessToken(User user)
    {
        Player player = playerRepository.findByUser_UserID(user.getUserID());
        Admin admin = adminRepository.findByUser_UserID(user.getUserID());
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .subject(user.getUsername())
                .userID(user.getUserID())
                .build();
        List<String> roles = new ArrayList<>();

        if(player != null)
        {
            roles.add(RoleEnum.PLAYER.toString());
        }
        if(admin != null)
        {
            roles.add(RoleEnum.ADMIN.toString());
        }
        if(player == null && admin == null)
        {
            throw new InvalidLoginException("USER_NOT_ASSIGNED_TO_ANY_ROLE");
        }

        accessTokenDTO.setRoles(roles);
        return accessTokenEncoder.encode(accessTokenDTO);
    }
}
