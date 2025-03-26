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

@SpringBootTest
@ActiveProfiles("test") // Uses test-specific configuration
public class HopeconnecttApplicationTests {
    
    @Test
    public void contextLoads() {
        // Empty test that just verifies Spring context loads
    }
}
