package com.nguyen1o2.service.imp;

import com.nguyen1o2.dto.UserDTO;
import com.nguyen1o2.payload.request.SignupRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignupRequest signupRequest);
}
