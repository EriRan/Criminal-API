package fi.eriran.criminalapi.testcore.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:hikari.properties"
)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CriminalApiSpringBootTest {
}
