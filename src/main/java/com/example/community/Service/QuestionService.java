package com.example.community.Service;

import com.example.community.Enum.CommentTypeEnum;
import com.example.community.Mapper.QuestionEXTMapper;
import com.example.community.Model.*;
import com.example.community.dto.ResoultEXDTO;
import com.example.community.exception.CustomErrorCode;
import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.exception.CustomErrorCode;
import com.example.community.exception.CustomException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    QuestionEXTMapper questionEXTMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalpage;
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        Integer totalcount = (int) questionMapper.countByExample(questionExample);
        //     算出总页数
        if (totalcount % size == 0) {
            totalpage = totalcount / size;
        } else {
            totalpage = (totalcount / size) + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalpage) {
            page = totalpage;
        }
        paginationDTO.setpagination(totalpage, page, size);
//        锁定页面

        Integer offset = size * (paginationDTO.getPage() - 1);

        QuestionExample example = new QuestionExample();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public PaginationDTO questionlist(Integer page, Integer size, Long id) {
        Integer totalpage;
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalcount = (int) questionMapper.countByExample(questionExample);
        //     算出总页数
        if (totalcount % size == 0) {
            totalpage = totalcount / size;
        } else {
            totalpage = (totalcount / size) + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalpage) {
            page = totalpage;
        }
        paginationDTO.setpagination(totalpage, page, size);
//        锁定页面

        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public QuestionDTO findbyid(Long id) throws ClassCastException {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;


    }

    public void createorupdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(1);
            question.setLikeCount(1);
            question.setCommentCount(0);
            question.setId(question.getId());
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }

        }

    }

    public void incview(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionEXTMapper.incview(question);

    }


}

