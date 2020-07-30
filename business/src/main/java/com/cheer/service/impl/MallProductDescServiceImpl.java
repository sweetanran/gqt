package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductDescDao;
import com.cheer.entity.MallProductDescEntity;
import com.cheer.service.MallProductDescService;
import org.springframework.stereotype.Service;

@Service("mallProductDescService")
public class MallProductDescServiceImpl extends ServiceImpl<MallProductDescDao, MallProductDescEntity> implements MallProductDescService {


}