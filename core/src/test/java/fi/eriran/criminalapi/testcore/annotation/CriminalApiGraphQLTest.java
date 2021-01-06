package fi.eriran.criminalapi.testcore.annotation;

import com.graphql.spring.boot.test.GraphQLTest;
import fi.eriran.criminalapi.configuration.ScalarConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test that fetches only the necessary classes for GraphQL to work
 */
@Import(ScalarConfiguration.class)
@GraphQLTest
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CriminalApiGraphQLTest {

}
