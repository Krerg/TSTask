package com.tsystems.Util;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by sasha_000 on 16.11.2014.
 */
@Aspect
public class TransactionHandler {

    @AfterReturning(value = "execution(String com.tsystems.services..*(..))")
    public void commitTransaction() {
        HibernateUtil.commitTransaction();
    }

    @Before("execution(String com.tsystems.services..*(..))")
    public void startTransaction() {
        HibernateUtil.startTransaction();
    }

    @AfterThrowing(pointcut = "execution(String com.tsystems.services..*(..))")
    public void rollback() {
        HibernateUtil.rollbackTransaction();
    }
}
