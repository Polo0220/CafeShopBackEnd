package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * 主題Mapper
 */
@Mapper
public interface MenuMapper {

    /**
     * 查詢全部主題數據
     * @return
     */
    @Select("select * from tb_menu")
    List<Menu> menuList();

    /**
     * 新增主題
     * @param menu
     */
    @Insert("insert into tb_menu(name, image, description, state, create_time, update_time) " +
            "values (#{name}, #{image}, #{description}, #{state}, #{createTime}, #{updateTime})")
    void insert(Menu menu);

    /**
     * 根據ID刪除主題
     * @param id
     */
    @Delete("delete from tb_menu where menu_id = #{id}")
    void deleteById(Integer id);

    /**
     * 修改主題
     * @param menu
     */
    void update(Menu menu);
}
