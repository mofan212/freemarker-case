package indi.mofan.templates;


import indi.mofan.util.FreeMarkerUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author mofan
 * @date 2024/10/23 10:23
 */
public class RecursiveNestingTest implements WithAssertions {

    @Test
    @SneakyThrows
    public void test() {
        var root = buildTemplateParam();

        String res = FreeMarkerUtil.precessTemplateToString("recursive-nesting.java.ftl", root);
        assertThat(res.trim()).isNotBlank()
                .hasLineCount(19)
                .contains("itemGrandson.addError(\"Error Info\");");
    }

    @Test
    @SneakyThrows
    public void testDiffPositionNestedLabel() {
        var root = buildTemplateParam();
        String res = FreeMarkerUtil.precessTemplateToString("recursive-nesting-2.java.ftl", root);
        assertThat(res.trim()).isNotBlank()
                .hasLineCount(25)
                .contains("dto.addError(\"Error Info\");");
    }

    private Map<String, Object> buildTemplateParam() {
        var entityMap = Map.of(
                "1", new Entity("Son", "son"),
                "2", new Entity("Parent", "parent"),
                "3", new Entity("Grandson", "grandson")
        );
        return Map.of(
                "entityMap", entityMap,
                "path", List.of("2", "1", "3")
        );
    }

    /**
     * 模板使用参数，不可修改 public 的访问修饰符
     */
    @Getter
    @AllArgsConstructor
    public static class Entity {
        private String type;
        private String name;
    }
}
