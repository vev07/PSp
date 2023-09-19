package de.telran.lesson3.logging_layer;

import de.telran.lesson3.service_layer.jpa.JpaCustomerService;
import de.telran.lesson3.service_layer.jpa.JpaProductService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class AspectLogging {
    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);
    private Logger logger2 = LoggerFactory.getLogger(JpaProductService.class);
    private Logger logger3 = LoggerFactory.getLogger(JpaCustomerService.class);

    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.*(..))")
    public void methodJpaProductService() {
    }

    @Before("methodJpaProductService()")
    public void thisMethodJpaProductServiceCall(JoinPoint joinPoint) {
        logger2.info(String.format("Method from class JpaProductService was started - %s", joinPoint.getSignature()));


    }

    @Around("methodJpaProductService()")
    public Object thisMethodJpaProductServiceEnding(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
            logger2.info(String.format("Method %s from class JpaProductService was ending with result %s",
                    joinPoint.getSignature(), result));
        } catch (Throwable e) {
            logger2.error("The method is giving false");
            throw new RuntimeException(e);
        }
        return result;
    }
    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaCustomerService.*(..))")
    public void methodJpaCustomerService() {
    }

    @Before("methodJpaCustomerService()")
    public void thisMethodJpaCustomerServiceCall(JoinPoint joinPoint) {
        logger3.info(String.format("Method from class JpaProductService was started - %s", joinPoint.getSignature()));


    }

    @Around("methodJpaCustomerService()")
    public Object thisMethodJpaCustomerServiceEnding(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
            logger3.info(String.format("Method %s from class JpaProductService was ending with result %s",
                    joinPoint.getSignature(), result));
        } catch (Throwable e) {
            logger3.error("The method is giving false");
            throw new RuntimeException(e);
        }
        return result;
    }


    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.add(..))")
    public void addProduct() {}
    @Before("addProduct()")
    public void beforeAddingProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
//        joinPoint.getSignature();
        logger.info(String.format("Method add called from class JpaProductService - %s", args[0]));
    }

    @Pointcut("execution(* de.telran.lesson3.controller.ProductController.test(..))")
    public void test() {}

    @Before("test()")
    public void beforeTest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info(String.format("Вызван метод test класса ProductController с входящим параметром %s.", args[0]));
    }

    @After("test()")
    public void afterTest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info(String.format("Закончил работу метод test класса ProductController, новое значение параметра -  %s.", args[0]));
    }

    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.getById(..))")
    public void getProductById() {}

    @AfterReturning("getProductById()")
    public void afterProductReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info(String.format("Метод getById класса JpaProductService успешно вернул продукт с идентификатором %d", args[0]));
    }

    @AfterThrowing("getProductById()")
    public void afterThrowingWhileProductReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info(String.format("Метод getById класса JpaProductService вызвал ошибку! Несуществующий идентификатор - %d", args[0]));
    }

    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.getCount(..))")
    public void getProductsCount() {}

//    @Around("getProductsCount()")
//    public Object aroundGetProductsCount(ProceedingJoinPoint joinPoint) {
//        logger.info("Around getProductsCount()");
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Around("getProductsCount()")
    public Object aroundGetProductsCount(ProceedingJoinPoint joinPoint) {
        logger.info("Around getProductsCount()");
        try {
            logger.info("Действительный результат - " + joinPoint.proceed());
            logger.info("Подменяем результат и возвращаем -1");
            return new Integer(-1);
        } catch (Throwable e) {
            logger.error("Тут какая-то ошибка!");
            throw new RuntimeException(e);
        }
    }
}
