package indi.mofan.templates;


import freemarker.template.Template;
import indi.mofan.conf.FreeMarkerConfig;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
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

        Template temp = FreeMarkerConfig.CONFIG.getTemplate("hello-world.html.ftl");
        StringWriter out = new StringWriter();
        temp.process(root, out);
        assertThat(out.toString()).isNotEmpty();
    }
}
