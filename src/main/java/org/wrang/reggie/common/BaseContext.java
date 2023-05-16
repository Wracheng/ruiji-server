package org.wrang.reggie.common;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal= new ThreadLocal<>();
    public static void setThreadLocal(Long id){
        threadLocal.set(id);
    }
    public static Long getThreadLocal(){
        return threadLocal.get();
    }
}
