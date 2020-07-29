package com.cheer.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.common.vo.Page;
import com.cheer.common.vo.PageParam;
import com.cheer.common.utils.PageQuery;

import com.cheer.dao.MallOrderDao;
import com.cheer.entity.MallOrderEntity;
import com.cheer.service.MallOrderService;

@Service("mallOrderService")
public class MallOrderServiceImpl extends ServiceImpl<MallOrderDao, MallOrderEntity> implements MallOrderService {


}