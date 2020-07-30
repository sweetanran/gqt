package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallImageDao;
import com.cheer.entity.MallImageEntity;
import com.cheer.service.MallImageService;
import org.springframework.stereotype.Service;

@Service("mallImageService")
public class MallImageServiceImpl extends ServiceImpl<MallImageDao, MallImageEntity> implements MallImageService {


}