package com.example.community.Service;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalpage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount= questionMapper.count();
        //     算出总页数
        if (totalcount % size == 0) {
            totalpage = totalcount / size;
        } else {
            totalpage = (totalcount / size) + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalpage){
            page=totalpage;
        }
        paginationDTO.setpagination(totalpage,page,size);
//        锁定页面

        Integer offset = size * (paginationDTO.getPage() - 1);
        List<Question> list = questionMapper.findlist(offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findbyid(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public PaginationDTO questionlist(Integer page, Integer size,Integer id) {
        Integer totalpage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount= questionMapper.questioncount(id);
        //     算出总页数
        if (totalcount % size == 0) {
            totalpage = totalcount / size;
        } else {
            totalpage = (totalcount / size) + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalpage){
            page=totalpage;
        }
        paginationDTO.setpagination(totalpage,page,size);
//        锁定页面

        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.findlistbyuserid(offset, size,id);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findbyid(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }
    }

