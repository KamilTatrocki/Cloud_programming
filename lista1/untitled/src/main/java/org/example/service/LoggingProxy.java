package org.example.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Arrays;
//
//public class LoggingProxy implements InvocationHandler {
//
//    private final Object target;
//    private final Logger logger;
//
//    private LoggingProxy(Object target) {
//        this.target = target;
//        this.logger = LoggerFactory.getLogger(target.getClass());
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T createProxy(T target, Class<T> interfaceClass) {
//        return (T) Proxy.newProxyInstance(
//                interfaceClass.getClassLoader(),
//                new Class<?>[]{interfaceClass},
//                new LoggingProxy(target)
//        );
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        logger.info("External Call: {} | Args: {}", method.getName(), Arrays.toString(args));
//        return method.invoke(target, args);
//    }
//}