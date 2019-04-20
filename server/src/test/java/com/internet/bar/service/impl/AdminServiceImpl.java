package com.internet.bar.service.impl;

import com.internet.bar.domain.entity.AdminEntity;
import com.internet.bar.mapper.AdminMapper;
import com.internet.bar.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements IAdminService {

}
