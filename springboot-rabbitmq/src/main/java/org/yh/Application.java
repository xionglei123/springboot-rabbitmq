package org.yh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		SpringApplication.run(Application.class, args);
		System.out.println("启动完成：耗时" + (System.currentTimeMillis() - start) + "ms");
	}
}
