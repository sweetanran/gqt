package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallComplaintDao;
import com.cheer.entity.MallComplaintEntity;
import com.cheer.service.MallComplaintService;
import org.springframework.stereotype.Service;

@Service("mallComplaintService")
public class MallComplaintServiceImpl extends ServiceImpl<MallComplaintDao, MallComplaintEntity> implements MallComplaintService {


}