package com.manageservice.users.dao;

import com.manageservice.users.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
public class UsersDaoImpl extends  BasicDao{

    private static final String COL_USERID = "user_id";
    private static final String COL_USERNAME = "username";
    private static final String COL_FIRSTNAME = "firstname";
    private static final String COL_LASTNAME = "lastname";
    private static final String COL_EMAIL = "user_email";
    private static final String COL_PHONE = "user_phone_num";


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public BigInteger createUser(final String userName, final String firstName, final String lastName, final String userEmail, final String userPhone){
        {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "insert into T_USERS(username,firstname,lastname,user_email,user_phone_num,created_at) values( :username, :firstname, :lastname, :user_email, :user_phone_num, SYSDATE())";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(COL_USERNAME, userName)
                    .addValue(COL_FIRSTNAME, firstName).addValue(COL_LASTNAME, lastName)
                    .addValue(COL_EMAIL,userEmail).addValue(COL_PHONE,userPhone);
            super.namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);
            return (BigInteger) keyHolder.getKey();
        }
    }

    @Transactional(readOnly = true)
    public User getUser(final BigInteger userID){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "SELECT * FROM T_USERS WHERE user_id=:user_id LIMIT 0, 1";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(COL_USERID,userID);
        RowMapper<User>  rowMapper = (resultSet, rowNum) -> User.builder().username(resultSet.getString(COL_USERNAME))
                .firstname(resultSet.getString(COL_FIRSTNAME)).lastname(resultSet.getString(COL_LASTNAME))
                .user_email(resultSet.getString(COL_EMAIL)).user_phone_num(resultSet.getString(COL_PHONE))
                .build();
        var user =  super.namedParameterJdbcTemplate.query(sql,namedParameters,rowMapper);
        return (user.isEmpty())? new User() : user.get(0);
    }



}
