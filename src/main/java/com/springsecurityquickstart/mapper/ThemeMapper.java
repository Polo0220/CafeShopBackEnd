package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.Theme;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * 主題Mapper
 */
@Mapper
public interface ThemeMapper {

    /**
     * 查詢全部主題數據
     * @return
     */
    @Select("select * from tb_theme")
    List<Theme> themeList();

    /**
     * 新增主題
     * @param theme
     */
    @Insert("insert into tb_theme(name, image, description, state, create_time, update_time) " +
            "values (#{name}, #{image}, #{description}, #{state}, #{createTime}, #{updateTime})")
    void insert(Theme theme);

    /**
     * 根據ID刪除主題
     * @param id
     */
    @Delete("delete from tb_theme where theme_id = #{id}")
    void deleteById(Integer id);

    /**
     * 修改主題
     * @param theme
     */
    void update(Theme theme);
}
