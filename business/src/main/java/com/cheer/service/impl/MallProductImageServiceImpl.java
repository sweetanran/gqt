package com.cheer.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.common.vo.Page;
import com.cheer.common.vo.PageParam;
import com.cheer.common.utils.PageQuery;

import com.cheer.dao.MallProductImageDao;
import com.cheer.entity.MallProductImageEntity;
import com.cheer.service.MallProductImageService;

@Service("mallProductImageService")
public class MallProductImageServiceImpl extends ServiceImpl<MallProductImageDao, MallProductImageEntity> implements MallProductImageService {


}