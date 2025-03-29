// package com.example.hopeconnectt;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// class HopeconnecttApplicationTests {

// 	@Test
// 	void contextLoads() {
// 	}

// }
package com.example.hopeconnectt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HopeconnecttApplicationTests {

    @Test
    void contextLoads() {
        // Test will pass if application context loads
    }
}
