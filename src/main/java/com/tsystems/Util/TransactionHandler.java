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

    @AfterReturning(value = "execution(* com.tsystems.daos..*(..))")
    public void commitTransaction() {
        HibernateUtil.commitTransaction();
    }

    @Before("execution(* com.tsystems.daos..*(..))")
    public void startTransaction()
    {
        HibernateUtil.startTransaction();
    }

    @AfterThrowing(pointcut = "execution(* com.tsystems.daos..*(..))")
    public void rollback() {
        HibernateUtil.rollbackTransaction();
    }
}
