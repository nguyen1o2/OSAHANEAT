package com.nguyen1o2.service;

import com.nguyen1o2.dto.UserDTO;
import com.nguyen1o2.entity.Roles;
import com.nguyen1o2.entity.Users;
import com.nguyen1o2.payload.request.SignupRequest;
import com.nguyen1o2.repository.UserRepository;
import com.nguyen1o2.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
            //@Qualifier("tenBean")
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser(){
        List<Users> list = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (Users users: list) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setPassWord(users.getPassWord());
            userDTO.setFullName(users.getFullName());
            userDTO.setCreateDate(users.getCreateDate());
            listUserDTO.add(userDTO);
        }

        return listUserDTO;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUserName(username);
        return passwordEncoder.matches(password,user.getPassWord());
    }

    @Override
    public boolean addUser(SignupRequest signupRequest) {
        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());

        Users users = new Users();
        users.setFullName(signupRequest.getFullName());
        users.setUserName(signupRequest.getEmail());
        users.setPassWord(signupRequest.getPassword());
        users.setRoles(roles);
        try{
            userRepository.save(users);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
