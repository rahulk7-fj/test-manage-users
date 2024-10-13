package com.manageservice.users.business;

import com.manageservice.users.dao.UsersDaoImpl;
import com.manageservice.users.model.ManageUserRequest;
import com.manageservice.users.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Component
@Slf4j
public class ManageUserBusiness {
    @Autowired
    private UsersDaoImpl userDao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public BigInteger create(final String trackingId, final ManageUserRequest request){

        log.debug("MannageUserRequest ={}",request.toString());

        var userID = userDao.createUser(request.getUser().getUsername(),
                request.getUser().getFirstname(),request.getUser().getLastname(),
                request.getUser().getUser_email(),request.getUser().getUser_phone_num());
        log.debug("UserId ={}", userID);
        return userID;

    }

    public User getUserData(final BigInteger userID){

        var userData = userDao.getUser(userID);
        return userData;

    }
}

