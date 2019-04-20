package com.internet.bar.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("record")
public class RecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String customerIdCard;

    /**
     * 秒为单位
     */
    private String duration;

    private Date startTime;

    private Date endTime;

    private Date dateCreate;

    private Date dateUpdate;

    private Date dateDelete;


    public static final String ID = "id";

    public static final String CUSTOMER_ID_CARD = "customer_id_card";

    public static final String DURATION = "duration";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String DATE_CREATE = "date_create";

    public static final String DATE_UPDATE = "date_update";

    public static final String DATE_DELETE = "date_delete";

}
