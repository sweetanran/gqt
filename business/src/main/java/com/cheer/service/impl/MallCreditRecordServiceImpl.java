package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallCreditRecordDao;
import com.cheer.entity.MallCreditRecordEntity;
import com.cheer.service.MallCreditRecordService;
import org.springframework.stereotype.Service;

@Service("mallCreditRecordService")
public class MallCreditRecordServiceImpl extends ServiceImpl<MallCreditRecordDao, MallCreditRecordEntity> implements MallCreditRecordService {


}