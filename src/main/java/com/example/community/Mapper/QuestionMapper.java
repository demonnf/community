package com.example.community.Mapper;

import com.example.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) " +
            "values (#{title},#{description},#{gmtcreate},#{gmtmodified},#{creator},#{tag})")
      void Create(Question question);
   @Select("select * from question limit #{offset},#{size}")
    List<Question> findlist(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
@Select("select count(1) from question")
    Integer count();
}
