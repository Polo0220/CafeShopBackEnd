package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.option.OptionChildren;
import org.apache.ibatis.annotations.*;
import com.springsecurityquickstart.pojo.option.Option;
import java.util.List;

@Mapper
public interface OptionMapper {
    /**
     * 查詢全部option
     * @return
     */
    @Select("SELECT * FROM tb_option")
    List<Option> optionList();

    @Select("SELECT * FROM tb_option_children WHERE option_id = #{optionId}")
    List<OptionChildren> selectOptionChildrenByOptionId(@Param("optionId") Integer optionId);

    /**
     * 新增option
     * @param option
     */
    @Insert("INSERT INTO tb_option(name, single_choice, type, create_time, update_time) " +
            "VALUES (#{name}, #{singleChoice}, #{type}, #{createTime}, #{updateTime})")
    void insertOption(Option option);

    /**
     * 根據ID刪除option
     * @param optionId
     */
    @Delete("DELETE FROM tb_option WHERE option_id = #{optionId}")
    void deleteById(Integer optionId);

    /**
     * 修改option(xml映射)
     * @param option
     */
    void updateOption(Option option);
}
