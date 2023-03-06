package com.sms.transfer.station.client;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author liubaixu
 * @description 消息详情
 * @date 2019/11/14
 */
@Data
public class MessageItem {
    private Long id;
    private String msgId;
    private String uniqueFlag;
    private String partitionOffset;
    private String systemName;
    private String templateCode;
    @NotBlank(message = "messageContent不能为空")
    private String messageContent;
    private String messageSign;
    private String messageFrom;
    private String messageTo;
    private String messageTitle;
    private String messageType;
    private String messageAlter;
    private String messageSt;
    private String sendDate;
    private String replyTo;
    private String backUp1;
    private String deleted = "0";
    private String gateWay;
}
