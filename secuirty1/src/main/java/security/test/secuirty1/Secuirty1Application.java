package security.test.secuirty1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Secuirty1Application {

	public static void main(String[] args) {
		SpringApplication.run(Secuirty1Application.class, args);
	}

}
