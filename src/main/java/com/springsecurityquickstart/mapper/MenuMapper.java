package com.springsecurityquickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springsecurityquickstart.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
