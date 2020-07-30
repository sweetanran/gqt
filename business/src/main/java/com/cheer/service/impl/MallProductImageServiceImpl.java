package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductImageDao;
import com.cheer.entity.MallProductImageEntity;
import com.cheer.service.MallProductImageService;
import org.springframework.stereotype.Service;

@Service("mallProductImageService")
public class MallProductImageServiceImpl extends ServiceImpl<MallProductImageDao, MallProductImageEntity> implements MallProductImageService {


}