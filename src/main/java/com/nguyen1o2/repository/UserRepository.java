package com.nguyen1o2.repository;

import com.nguyen1o2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    List<Users> findByUserNameAndPassWord(String username, String password);
    Users findByUserName(String username);
}
