package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MemberDao;
import com.cheer.entity.MemberEntity;
import com.cheer.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * @author cheer
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {


}
