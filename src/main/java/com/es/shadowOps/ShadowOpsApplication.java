package com.es.shadowOps;

import com.es.shadowOps.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableAsync
public class ShadowOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShadowOpsApplication.class, args);
	}

}
