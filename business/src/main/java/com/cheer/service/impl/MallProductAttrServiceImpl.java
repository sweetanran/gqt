package com.cheer.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.common.vo.Page;
import com.cheer.common.vo.PageParam;
import com.cheer.common.utils.PageQuery;

import com.cheer.dao.MallProductAttrDao;
import com.cheer.entity.MallProductAttrEntity;
import com.cheer.service.MallProductAttrService;

@Service("mallProductAttrService")
public class MallProductAttrServiceImpl extends ServiceImpl<MallProductAttrDao, MallProductAttrEntity> implements MallProductAttrService {


}