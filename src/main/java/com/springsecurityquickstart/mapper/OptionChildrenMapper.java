package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.option.OptionChildren;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OptionChildrenMapper {

    /**
     * 根據ID查詢子選項
     * @param optionId
     * @return
     */
    @Select("SELECT * FROM tb_option_children WHERE option_id = #{optionId}")
    List<OptionChildren> selectOptionChildrenByOptionId(Integer optionId);

    /**
     * 新增子選項
     * @param child
     */
    @Insert("INSERT INTO tb_option_children(name, selected, price, option_id, create_time, update_time) " +
            "VALUES (#{name}, #{selected}, #{price}, #{optionId}, #{createTime}, #{updateTime})")
    void insertOptionChildren(OptionChildren child);

    /**
     * 刪除子選項(option刪除時連同刪除)
     * @param optionId
     */
    @Delete("DELETE FROM tb_option_children WHERE option_id = #{optionId}")
    void deleteByOptionId(Integer optionId);

    /**
     * 刪除子選項(單獨刪除子選項)
     * @param childId
     */
    @Delete("DELETE FROM tb_option_children WHERE option_children_id = #{childId}")
    void deleteOptionChildrenById(Integer childId);

    /**
     * 修改子選項(xml映射)
     * @param child
     */
    void updateOptionChildren(OptionChildren child);
}
