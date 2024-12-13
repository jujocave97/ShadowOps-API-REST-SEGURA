package com.es.shadowOps;

import com.es.shadowOps.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ShadowOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShadowOpsApplication.class, args);
	}

}
