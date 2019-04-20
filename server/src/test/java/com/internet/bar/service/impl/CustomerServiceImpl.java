package com.internet.bar.service.impl;

import com.internet.bar.domain.entity.CustomerEntity;
import com.internet.bar.mapper.CustomerMapper;
import com.internet.bar.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerEntity> implements ICustomerService {

}
