package ninja.optimistic.api.posts.health

import ninja.optimistic.api.posts.BaseServiceSpec
import ninja.optimistic.api.posts.controller.PostsController
import org.springframework.beans.factory.annotation.Autowired

class ContextLoadsSpec extends BaseServiceSpec {
  @Autowired(required = false)
  private PostsController postsController

  def "when context is loaded then all expected beans are created"() {
    expect: "the PostController is created"
    postsController
  }
}