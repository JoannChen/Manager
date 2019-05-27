package com.zuoyu.manager.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;


/**
 * <pre>
 * Function：Log工具类
 *
 * Created by Joann on 17/2/6 11:28
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LogUtil {
    private static String customTagPrefix = "";

    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;
    private static boolean allowWtf = true;
    private static CustomLogger customLogger;


    public static void closeLog(){
        allowD = false;
        allowE = false;
        allowI = false;
        allowV = false;
        allowW = false;
        allowWtf = false;
    }

    public static void d(String content) {
        if(allowD) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.d(tag, content);
            } else {
                Log.d(tag, content);
            }

        }
    }

    public static void d(String content, Throwable tr) {
        if(allowD) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.d(tag, content, tr);
            } else {
                Log.d(tag, content, tr);
            }

        }
    }

    public static void e(String content) {
        if(allowE) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.e(tag, content);
            } else {
                Log.e(tag, content);
            }

        }
    }

    public static void e(String content, Throwable tr) {
        if(allowE) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.e(tag, content, tr);
            } else {
                Log.e(tag, content, tr);
            }

        }
    }

    public static void i(String content) {
        if(allowI) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.i(tag, content);
            } else {
                Log.i(tag, content);
            }

        }
    }

    public static void i(String content, Throwable tr) {
        if(allowI) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.i(tag, content, tr);
            } else {
                Log.i(tag, content, tr);
            }

        }
    }

    public static void v(String content) {
        if(allowV) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.v(tag, content);
            } else {
                Log.v(tag, content);
            }

        }
    }

    public static void v(String content, Throwable tr) {
        if(allowV) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.v(tag, content, tr);
            } else {
                Log.v(tag, content, tr);
            }

        }
    }

    public static void w(String content) {
        if(allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.w(tag, content);
            } else {
                Log.w(tag, content);
            }

        }
    }

    public static void w(String content, Throwable tr) {
        if(allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.w(tag, content, tr);
            } else {
                Log.w(tag, content, tr);
            }

        }
    }

    public static void w(Throwable tr) {
        if(allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.w(tag, tr);
            } else {
                Log.w(tag, tr);
            }

        }
    }

    public static void wtf(String content) {
        if(allowWtf) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.wtf(tag, content);
            } else {
                Log.wtf(tag, content);
            }

        }
    }

    public static void wtf(String content, Throwable tr) {
        if(allowWtf) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.wtf(tag, content, tr);
            } else {
                Log.wtf(tag, content, tr);
            }

        }
    }

    public static void wtf(Throwable tr) {
        if(allowWtf) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if(customLogger != null) {
                customLogger.wtf(tag, tr);
            } else {
                Log.wtf(tag, tr);
            }

        }
    }


    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(Locale.getDefault(),tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public interface CustomLogger {

        void d(String var1, String var2);

        void d(String var1, String var2, Throwable var3);

        void e(String var1, String var2);

        void e(String var1, String var2, Throwable var3);

        void i(String var1, String var2);

        void i(String var1, String var2, Throwable var3);

        void v(String var1, String var2);

        void v(String var1, String var2, Throwable var3);

        void w(String var1, String var2);

        void w(String var1, String var2, Throwable var3);

        void w(String var1, Throwable var2);

        void wtf(String var1, String var2);

        void wtf(String var1, String var2, Throwable var3);

        void wtf(String var1, Throwable var2);
    }
}
