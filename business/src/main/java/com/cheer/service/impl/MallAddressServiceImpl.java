package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallAddressDao;
import com.cheer.entity.MallAddressEntity;
import com.cheer.service.MallAddressService;
import org.springframework.stereotype.Service;

@Service("mallAddressService")
public class MallAddressServiceImpl extends ServiceImpl<MallAddressDao, MallAddressEntity> implements MallAddressService {


}