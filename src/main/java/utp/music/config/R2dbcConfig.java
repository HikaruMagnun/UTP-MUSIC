/*
 * package utp.music.config;
 * 
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.core.io.ClassPathResource;
 * import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
 * import
 * org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
 * import
 * org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
 * import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
 * 
 * import io.r2dbc.spi.ConnectionFactory;
 * 
 * @Configuration
 * public class R2dbcConfig extends AbstractR2dbcConfiguration {
 * 
 * @Bean
 * public ConnectionFactoryInitializer initializer(ConnectionFactory
 * connectionFactory) {
 * ConnectionFactoryInitializer initializer = new
 * ConnectionFactoryInitializer();
 * initializer.setConnectionFactory(connectionFactory);
 * 
 * // No need to set a populator if your schema is already created via Docker
 * // If you want to run scripts on startup, you can uncomment this
 * // ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new
 * // ClassPathResource("schema.sql"));
 * // initializer.setDatabasePopulator(populator);
 * 
 * return initializer;
 * }
 * 
 * @Bean
 * 
 * @Override
 * public ConnectionFactory connectionFactory() {
 * return super.connectionFactory();
 * }
 * }
 */