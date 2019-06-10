package com.blogs.service;

import com.blogs.dao.AuditDao;
import com.blogs.entity.AuditEntity;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl extends AbstractBaseService<AuditEntity, AuditDao> implements AuditService {

    public AuditServiceImpl(AuditDao dao) {
        super(dao);
    }

}
