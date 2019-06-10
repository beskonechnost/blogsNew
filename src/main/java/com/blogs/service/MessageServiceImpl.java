package com.blogs.service;

import com.blogs.dao.MessageDao;
import com.blogs.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends AbstractBaseService<Message, MessageDao> implements MessageService {

    public MessageServiceImpl(MessageDao dao) {
        super(dao);
    }

    @Override
    public List<Message> findAllByUserId(Long id) {
        return dao.findAllByUser_Id(id);
    }
}
