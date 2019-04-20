package com.cha.internet.bar.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("recharge_record")
public class RechargeRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String idCard;

    private String changeMoney;

    /**
     *  ADD REDUCE
     */
    private String type;


    private Date dateCreate;

    private Date dateUpdate;

    private long dateDelete;


    public static final String ID = "id";

    public static final String ID_CARD = "id_card";

    public static final String CHANGE_MONEY = "change_money";

    public static final String TYPE = "type";

    public static final String DATE_CREATE = "date_create";

    public static final String DATE_UPDATE = "date_update";

    public static final String DATE_DELETE = "date_delete";

}
