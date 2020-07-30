package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductAttrDao;
import com.cheer.entity.MallProductAttrEntity;
import com.cheer.service.MallProductAttrService;
import org.springframework.stereotype.Service;

@Service("mallProductAttrService")
public class MallProductAttrServiceImpl extends ServiceImpl<MallProductAttrDao, MallProductAttrEntity> implements MallProductAttrService {


}