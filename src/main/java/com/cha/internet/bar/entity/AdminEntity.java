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
@TableName("admin")
public class AdminEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String mobile;

    private String password;

    private Date dateCreate;

    private Date dateUpdate;

    private long dateDelete;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String MOBILE = "mobile";

    public static final String PASSWORD = "password";

    public static final String DATE_CREATE = "date_create";

    public static final String DATE_UPDATE = "date_update";

    public static final String DATE_DELETE = "date_delete";

}
