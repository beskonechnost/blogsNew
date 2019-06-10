package com.blogs.dao;

import com.blogs.entity.Message;

import java.util.List;

public interface MessageDao extends BaseDao<Message> {

    List<Message> findAllByUser_Id (Long userId);

}
