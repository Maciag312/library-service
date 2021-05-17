package pwr.awt.demo.infrastructure.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {JpaUserRepositoryImpl.class, JpaBookRepositoryImpl.class, JpaBookRentalRepositoryImpl.class}, considerNestedRepositories = true)
@EntityScan(basePackageClasses = {JpaUserRepositoryImpl.class, JpaBookRepositoryImpl.class, JpaBookRentalRepositoryImpl.class})
public class JpaDBConfiguration {

    @Bean
    JpaUserRepositoryImpl userRepository(JpaUserRepositoryImpl.JpaUserRepo jpaUserRepo){
        return new JpaUserRepositoryImpl(jpaUserRepo);
    }

    @Bean
    JpaBookRepositoryImpl bookRepository(JpaBookRepositoryImpl.JpaBookRepo jpaBookRepo){
        return new JpaBookRepositoryImpl(jpaBookRepo);
    }

    @Bean
    JpaBookRentalRepositoryImpl bookRentalRepository(JpaBookRentalRepositoryImpl.JpaBookRentalRepo jpaBookRentalRepo){
        return new JpaBookRentalRepositoryImpl(jpaBookRentalRepo);
    }

}
