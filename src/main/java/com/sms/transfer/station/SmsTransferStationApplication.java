package com.sms.transfer.station;

import com.sms.transfer.station.client.SmsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SmsTransferStationApplication {

    private static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SmsTransferStationApplication.class, args);
        try {
            Resource resource = resolver.getResource("classpath:sms/sms.txt");
            SmsClient smsClient = context.getBean(SmsClient.class);
            List<String> lines = Files.readAllLines(Paths.get(resource.getFile().getPath()));
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("----------------" + lines.get(i) + "----------------------------");
                smsClient.sendMessage(lines.get(i), "感谢使用爱东港APP, 如有疑问，请致电：7149008", "爱东港");
                if (i % 10 == 1) {
                    Thread.sleep(1000l);
                }
            }
            System.out.println("本次发送结束: " + lines);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        context.close();
    }

}
