package com.sms.transfer.station.client;

import lombok.Data;

/**
 * @author liubaixu
 * @description 消息的处理结果
 * @date 2019/11/15
 */
@Data
public class MessageResult {
    private Long id;
    private String uniqueFlag;
    /**
     * 0:成功
     * 1：失败
     */
    private String result;
    //系统名称
    private String systemName;
    private String messageType;
}
