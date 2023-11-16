package ps;

import java.net.http.HttpHeaders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClaseSpringApplicationTests {

	@LocalServerPort
	private int port;

	// se utilizan para hacer las peticiones a nuestro cliente HTTP
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	void contextLoads() {
	}

}
