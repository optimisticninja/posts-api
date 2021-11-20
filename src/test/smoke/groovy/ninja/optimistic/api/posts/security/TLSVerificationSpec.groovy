package ninja.optimistic.api.posts.security

import io.netty.channel.StacklessClosedChannelException
import ninja.optimistic.api.posts.BaseServiceSpec
import ninja.optimistic.test.common.TestConstants
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import reactor.netty.http.client.PrematureCloseException

import javax.net.ssl.SSLHandshakeException

class TLSVerificationSpec extends BaseServiceSpec {

  def "when insecure http request is sent then rejected"() {
    given:
    def client = WebTestClient
      .bindToServer()
      .baseUrl(getBaseUrl().replace(TestConstants.SCHEME_HTTPS, TestConstants.SCHEME_HTTP))
      .build()
    when:
    client
      .get()
      .uri("/posts/${UUID.randomUUID()}")
      .exchange()
    then:
    def e = thrown(WebClientRequestException)
    expect:
    e.cause instanceof PrematureCloseException
  }

  def "when TLSv1.1 request sent then rejected"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_1)
    when:
    client
      .get()
      .uri("/posts/${UUID.randomUUID()}")
      .exchange()
    then:
    def e = thrown(WebClientRequestException)
    expect:
    e.cause instanceof SSLHandshakeException
    e.message.contains(TestConstants.HANDSHAKE_EXCEPTION_MSG)
  }

  def "when secured TLSv1.2 request sent then response ok"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_2)
    expect:
    client
      .get()
      .uri("/posts")
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().isOk()
  }

  def "when secured TLSv1.3 request sent then response ok"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_3)
    expect:
    client
      .get()
      .uri("/posts")
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().isOk()
  }

  def "when TLSv1.3 allowed cipher suite used then accepted"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_3, List.of("TLS_CHACHA20_POLY1305_SHA256"))
    expect:
    client
      .get()
      .uri("/posts")
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().isOk()
  }

  def "when TLSv1.2 allowed cipher suite used then accepted"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_2, List.of("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384"))
    expect:
    client
      .get()
      .uri("/posts")
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus().isOk()
  }

  def "when disallowed cipher suite used then rejected"() {
    given:
    def client = getHttpClient(TestConstants.TLSV1_2, List.of(TestConstants.DISALLOWED_CIPHER_SUITE))
    when:
    client
      .get()
      .uri("/posts/${UUID.randomUUID()}")
      .exchange()
    then:
    def e = thrown(WebClientRequestException)
    expect:
    e.cause instanceof StacklessClosedChannelException
  }
}