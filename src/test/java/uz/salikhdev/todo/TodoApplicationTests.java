package uz.salikhdev.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoApplicationTests {

  /*  @Container
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2")
            .withDatabaseName("postgres")
            .withUsername("user")
            .withPassword("password");

    @DynamicPropertySource
    public static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }*/


    @Test
    public void contextLoads() {
    }

}
