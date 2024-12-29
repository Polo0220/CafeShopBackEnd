package com.springsecurityquickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springsecurityquickstart.domain.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper extends BaseMapper<User> {
}
