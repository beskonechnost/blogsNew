package com.blogs.service;

import com.blogs.entity.Message;

import java.util.List;

public interface MessageService extends BaseService<Message> {

    List<Message> findAllByUserId (Long id);

}
