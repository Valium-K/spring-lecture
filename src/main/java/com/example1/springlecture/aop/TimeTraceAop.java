package com.example1.springlecture.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP는 정형화된 코드가 아니라 @Component로 컴포넌트 스캔보단 직접 SpringBean으로 등록한다.
@Component
@Aspect
public class TimeTraceAop {

    // 자주 쓰이는 것만 쓰니 필요할때마다 검색해 쓰면 된다.
    @Around("execution(* com.example1.springlecture..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        // 예외까지 시간측정을 위해 try-catch로 묶었다.
        try {
            // 프록시(여기선 execute())를 실행 후 실제 메서드를 실행한다.
            return joinPoint.proceed();
        } finally {
            // 실제 메서드가 return된 후 프록시의 finally문을 실행한다.
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.getThis() + " " + timeMs + "ms");
        }
    }
}
