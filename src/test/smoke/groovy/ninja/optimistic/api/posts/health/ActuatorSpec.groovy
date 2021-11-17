package ninja.optimistic.api.posts.health

import ninja.optimistic.api.posts.BaseServiceSpec
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class ActuatorSpec extends BaseServiceSpec {
  def "when actuator health request sent then status OK"() {
    given:
    def client = getHttpClient()
    expect:
    client
      .get()
      .uri("/actuator/health")
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().isOk()
  }
}