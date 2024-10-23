package indi.mofan.config;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author mofan
 * @date 2024/10/21 17:58
 */
public class FreeMarkerConfig {

    public static final Configuration CONFIG;

    static {
        CONFIG = new Configuration(Configuration.VERSION_2_3_22);
        try {
            URL url = FreeMarkerConfig.class.getClassLoader().getResource("templates/");
            if (url == null) {
                throw new FileNotFoundException("templates not found");
            }
            CONFIG.setDirectoryForTemplateLoading(new File(url.getFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CONFIG.setDefaultEncoding("UTF-8");
        CONFIG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

}
