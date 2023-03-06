package com.sms.transfer.station.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信发送记录
 * </p>
 *
 * @author chenkw
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_md_sms_record")
@ApiModel(value="TMdSmsRecord对象", description="短信发送记录")
public class TMdSmsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "短信内容")
    private String messageContent;

    @ApiModelProperty(value = "短信Sign")
    private String messageSign;

    @ApiModelProperty(value = "发送的日志ID")
    private String journalId;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "发送是否成功")
    private Boolean success;

    @ApiModelProperty(value = "有效性状态（0.未删除、1.已删除 默认值：0）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "待定1")
    private String other1;

    @ApiModelProperty(value = "待定2")
    private String other2;

    @ApiModelProperty(value = "待定3")
    private String other3;

    @ApiModelProperty(value = "待定4")
    private String other4;

    @ApiModelProperty(value = "待定5")
    private String other5;


}
