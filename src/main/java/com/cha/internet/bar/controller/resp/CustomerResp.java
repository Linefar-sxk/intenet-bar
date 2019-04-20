package com.cha.internet.bar.controller.resp;

import com.cha.internet.bar.entity.CustomerEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerResp extends CustomerEntity {

    private Date startTime;

    private Date endTime;
}
