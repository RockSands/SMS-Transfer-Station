package com.sms.transfer.station.job;

import com.sms.transfer.station.service.ITMdSmsJournalService;
import com.sms.transfer.station.service.ITMdSmsRecordService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableScheduling
@Component
public class SmsReadRecordJob {

    @Resource
    private ITMdSmsRecordService smsRecordService;

    @Resource
    private ITMdSmsJournalService smsJournalService;

    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    public void readRecord() {

    }

}
