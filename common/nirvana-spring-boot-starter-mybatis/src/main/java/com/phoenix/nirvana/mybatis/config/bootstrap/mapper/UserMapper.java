package com.phoenix.nirvana.mybatis.config.bootstrap.mapper;

import com.phoenix.nirvana.mybatis.config.bootstrap.User;

public interface UserMapper {

    User selectById(int id);
}
