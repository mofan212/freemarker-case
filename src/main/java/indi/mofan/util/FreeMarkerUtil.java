package indi.mofan.util;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import indi.mofan.config.FreeMarkerConfig;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author mofan
 * @date 2024/10/23 10:15
 */
public final class FreeMarkerUtil {

    private FreeMarkerUtil() {
    }

    public static String precessTemplateToString(String templateName, Object param) throws IOException, TemplateException {
        Template temp = FreeMarkerConfig.CONFIG.getTemplate(templateName);
        StringWriter out = new StringWriter();
        temp.process(param, out);
        return out.toString();
    }

}
