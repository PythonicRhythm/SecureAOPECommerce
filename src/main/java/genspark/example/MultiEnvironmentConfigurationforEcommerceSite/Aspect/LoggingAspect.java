package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Aspect;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class LoggingAspect {

    @Before("execution(* genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl.*(..))")
    public void logBeforeService(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.toString(args);
        log.info("Executing method: " + methodName + ", Arguments: " + argsString);
    }

    @AfterReturning(pointcut = "execution(* genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl.*(..))", returning = "result")
    public void logAfterService(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = IntStream.range(0, args.length)
                .mapToObj(i -> {
                    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                    Parameter parameter = method.getParameters()[i];
                    return parameter.getName() + "=" + args[i];
                })
                .collect(Collectors.joining(", "));
        log.info("Method executed: " + methodName + ", Arguments: " + argsString +", Return: " + result);
        if (isEmpty(result)){
            log.info("No Product with the criteria: " + argsString);
        }
    }

    @AfterReturning(pointcut = "execution(* genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation.ProductValidator.validate(..))", returning = "result")
    public void logAfterValidation(JoinPoint joinPoint, Object result){ 
        Object[] args = joinPoint.getArgs();
        Object target = args[0];
        String methodName = joinPoint.getSignature().getName();

        if (target instanceof List<?> products) {
            int index = 0;
            for (Object obj: products){
                if (obj instanceof Product product){
                    if (logProductValidation(methodName,product,index,result)){
                        log.info("Executing method: " + methodName + ", Return: " + result);
                    }
                    index++;
                }
            }
        }
    }

    private boolean logProductValidation(String methodName, Product product, int index, Object result) {
        if (isEmpty(result)){
            if (product.getName() == null || product.getName().isEmpty()) {
                log.error("Executing method with error: " +methodName+" : Name must not be empty for product at index " + index + " : " + product);
            }
            if (product.getSeller() == null || product.getSeller().isEmpty()) {
                log.error("Executing method with error: " +methodName+" : Seller must not be empty for product at index " + index + " : " + product);
            }
            if (product.getPrice() == 0) {
                log.error("Executing method with error: " +methodName+" : Price must not be empty for product at index " + index + " : " + product);
            }
            if (product.getDescription() == null || product.getDescription().isEmpty()) {
                log.error("Executing method with error: " +methodName+" : Description must not be empty for product at index " + index + " : " + product);
            }
            if (product.getCategories() == null || product.getCategories().length == 0) {
                log.error("Executing method with error: " +methodName+" : Categories must not be empty for product at index " + index + " : " + product);
            }
            return false;
        } else {
            return true;
        }

    }

    private boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj.getClass().isArray()) {
            return Arrays.asList((Object[]) obj).isEmpty();
        }
        if (obj instanceof Product){
            return  ((Product) obj).getName().isEmpty();
        }
        return false;
    }


}
