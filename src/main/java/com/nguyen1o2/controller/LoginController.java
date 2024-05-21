package com.nguyen1o2.controller;

import com.nguyen1o2.payload.ResponseData;
import com.nguyen1o2.payload.request.SignupRequest;
import com.nguyen1o2.service.imp.LoginServiceImp;
import com.nguyen1o2.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

   @Autowired
   LoginServiceImp loginServiceImp;

   @Autowired
   JwtUtilsHelper jwtUtilsHelper;
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String bryptKey = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(bryptKey);

        if(loginServiceImp.checkLogin(username,password)){
            String data = jwtUtilsHelper.generatedToken(username);
            responseData.setData(data);
        }else {
            responseData.setData("");
            responseData.setSucsess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/getalluser")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.getAllUser());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
