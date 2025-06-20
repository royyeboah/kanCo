package org.roy.kanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class KanCoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanCoApplication.class, args);
    }

}
