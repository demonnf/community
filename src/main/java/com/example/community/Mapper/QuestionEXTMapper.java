package com.example.community.Mapper;

import com.example.community.Model.Question;
import com.example.community.Model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionEXTMapper {
    int  incview( Question question);
    int  incCommentcount( Question question);
}