package com.nguyen1o2.service;

import com.nguyen1o2.dto.UserDTO;
import com.nguyen1o2.entity.Users;
import com.nguyen1o2.repository.UserRepository;
import com.nguyen1o2.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> list = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (Users users: list) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setFullName(users.getFullName());
            userDTO.setUserName(users.getUserName());
            userDTO.setPassWord(users.getPassWord());
            userDTO.setCreateDate(users.getCreateDate());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
