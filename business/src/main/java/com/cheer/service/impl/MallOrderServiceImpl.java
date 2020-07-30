package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallOrderDao;
import com.cheer.entity.MallOrderEntity;
import com.cheer.service.MallOrderService;
import org.springframework.stereotype.Service;

@Service("mallOrderService")
public class MallOrderServiceImpl extends ServiceImpl<MallOrderDao, MallOrderEntity> implements MallOrderService {


}