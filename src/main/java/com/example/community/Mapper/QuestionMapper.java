package com.example.community.Mapper;

import com.example.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(title,description,gmt_create ,gmt_modified,creator,tag)values(#{title},#{description},#{gmt_create}，#{gmt_modified}，#{creator}，#{tag})")
    public void Create(Question question);
}
