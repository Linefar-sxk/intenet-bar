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
@TableName("customer")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String idCard;

    private String money;

    /**
     * YEAR MONTH
     */
    private String type;

    /**
     * 激活状态
     */
    private Integer activationState;

    private String password;

    private Date dateCreate;

    private Date dateUpdate;

    private Date dateDelete;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String MONEY = "money";

    public static final String ID_CARD = "id_card";

    public static final String ACTIVATION_STATE = "activation_state";

    public static final String PASSWORD = "password";

    public static final String DATE_CREATE = "date_create";

    public static final String DATE_UPDATE = "date_update";

    public static final String DATE_DELETE = "date_delete";

}
