package com.lxl.utils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import java.util.List;

public class CustomFreemarkerServlet extends freemarker.ext.servlet.FreemarkerServlet {

    private static ApplicationContext context;

    @Override
    public void init() throws ServletException {

        super.init();

        getConfiguration().setSharedVariable("dic", new TemplateMethodModelEx() {
            @Override
            public Object exec(List arguments) throws TemplateModelException {
                return null;
            }
        });


        getConfiguration().setSharedVariable("ysSub", new TemplateMethodModelEx() {
            @Override
            public Object exec(List arguments) throws TemplateModelException {
                String result = "";
                if (arguments.size() == 2) {
                    String str = String.valueOf(arguments.get(0)).trim();
                    if (!str.equals("null")) {
                        int len = Integer.parseInt(String.valueOf(arguments.get(1)));
                        if (str.length() > len) {
                            return bSubstring(str, len * 2);
                        } else {
                            return str;
                        }
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            }
        });

    }

    public static String bSubstring(String s, int length) {
        try {
            byte[] bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 前两个字节是标志位，bytes[0] = -2，bytes[1] = -1。所以从第3位开始截取。
            for (; i < bytes.length && n < length; i++) {
                // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                if (i % 2 == 1) {
                    n++; // 在UCS2第二个字节时n加1
                } else {
                    // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                }
            }
            // 如果i为奇数时，处理成偶数
            if (i % 2 == 1)

            {
                // 该UCS2字符是汉字时，去掉这个截一半的汉字
                if (bytes[i - 1] != 0) {
                    i = i - 1;
                }
                // 该UCS2字符是字母或数字，则保留该字符
                else {
                    i = i + 1;
                }
            }

            return new String(bytes, 0, i, "Unicode");
        } catch (Exception se) {
            return "";
        }
    }
}
