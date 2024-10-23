package indi.mofan.templates;


import indi.mofan.util.FreeMarkerUtil;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author mofan
 * @date 2024/10/22 14:25
 */
public class HelloWorldTest implements WithAssertions {
    @Test
    @SneakyThrows
    public void test() {
        var root = new HashMap<String, Object>();
        root.put("user", "Big Joe");
        var latest = new HashMap<String, Object>();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        String res = FreeMarkerUtil.precessTemplateToString("hello-world.html.ftl", root);
        assertThat(res).isNotEmpty()
                .doesNotContain("user", "latestProduct.url", "latestProduct.name")
                .contains("Big Joe", "products/greenmouse.html", "green mouse");
    }
}
