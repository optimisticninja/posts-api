package ninja.optimistic.api.posts

import groovy.util.logging.Slf4j
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import ninja.optimistic.test.common.TestConstants
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.netty.http.client.HttpClient
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.constraints.NotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ PostsApiApplication.class ])
@ActiveProfiles("local")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = [BaseServiceSpec.Initializer.class])
abstract class BaseServiceSpec extends Specification {
  @Value('${spring.webflux.base-path}')
  @NotNull(message = "spring.webflux.base-path couldn't be retrieved from application.yml")
  private String basePath

  @LocalServerPort
  private int port

  @Shared
  protected static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13.4")
  .withDatabaseName("smoke-tests-db")
  .withUsername("sa")
  .withPassword("sa")

  static class Initializer
  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl().replace("jdbc", "r2dbc"),
        "spring.datasource.username=" + postgreSQLContainer.getUsername(),
        "spring.datasource.password=" + postgreSQLContainer.getPassword(),
        "spring.flyway.url=" + postgreSQLContainer.getJdbcUrl(),
        "spring.flyway.user=" + postgreSQLContainer.getUsername(),
        "spring.flyway.password=" + postgreSQLContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment())
    }
  }

  protected def getBaseUrl() {
    "https://localhost" + ":" + port + basePath
  }

  protected def getHttpClient(String protocol) {
    def httpClient = HttpClient
      .create().secure({
        it.sslContext(SslContextBuilder
          .forClient()
          .trustManager(InsecureTrustManagerFactory.INSTANCE)
          .protocols(protocol)
          .ciphers(TestConstants.CIPHER_SUITES)
          .build())
      })
    WebTestClient
      .bindToServer(new ReactorClientHttpConnector(httpClient))
      .baseUrl(getBaseUrl())
      .build()
  }

  protected def getHttpClient() {
    def httpClient = HttpClient
      .create().secure({
        it.sslContext(SslContextBuilder
          .forClient()
          .trustManager(InsecureTrustManagerFactory.INSTANCE)
          .protocols(TestConstants.TLSV1_3)
          .ciphers(TestConstants.CIPHER_SUITES)
          .build())
      })
    WebTestClient
      .bindToServer(new ReactorClientHttpConnector(httpClient))
      .baseUrl(getBaseUrl())
      .build()
  }

  protected def getHttpClient(String protocol, List<String> cipherSuites) {
    def httpClient = HttpClient
      .create().secure({
        it.sslContext(SslContextBuilder
          .forClient()
          .trustManager(InsecureTrustManagerFactory.INSTANCE)
          .protocols(protocol)
          .ciphers(cipherSuites)
          .build())
      })
    WebTestClient
      .bindToServer(new ReactorClientHttpConnector(httpClient))
      .baseUrl(getBaseUrl())
      .build()
  }

  def setupSpec() {
    postgreSQLContainer.start()
  }

  def cleanupSpec() {
    postgreSQLContainer.stop()
  }
}