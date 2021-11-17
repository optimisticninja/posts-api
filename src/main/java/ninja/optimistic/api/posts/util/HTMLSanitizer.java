package ninja.optimistic.api.posts.util;

import lombok.experimental.UtilityClass;
import org.owasp.html.Sanitizers;

@UtilityClass
public class HTMLSanitizer {
  public String sanitize(String markdown) {
    markdown = Sanitizers.BLOCKS.sanitize(markdown);
    markdown = Sanitizers.FORMATTING.sanitize(markdown);
    markdown = Sanitizers.IMAGES.sanitize(markdown);
    markdown = Sanitizers.LINKS.sanitize(markdown);
    return Sanitizers.STYLES.sanitize(markdown);
  }
}
