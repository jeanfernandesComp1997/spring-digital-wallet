package com.example.digitalwalletevents.application.configuration

import java.time.Duration
import java.time.Instant
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    val logger: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Around("@annotation(com.example.digitalwalletevents.core.domain.annotations.Loggable)")
    fun logExecutionTime(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val methodSignature = proceedingJoinPoint.signature as MethodSignature
        val className = methodSignature.declaringType.simpleName
        val methodName = methodSignature.method.name
        val parameters = methodSignature.parameterNames
        val parametersTypes = methodSignature.parameterTypes
        val startTime = Instant.now()

        logger.info(
            "Enter method - Class Name: {}, Method Name: {}, Parameters: {}, Parameters type: {}",
            className, methodName, parameters, parametersTypes
        )

        try {
            val result = proceedingJoinPoint.proceed()
            val elapsedTime: Long = Duration.between(startTime, Instant.now()).toMillis()
            logger.info(
                "Class Name: {}, Method Name: {}, Elapsed Time: {}ms",
                className, methodName, elapsedTime
            )
            logger.info("Result: {}", result)
            return result
        } catch (exception: Exception) {
            val elapsedTime = Duration.between(startTime, Instant.now()).toMillis()
            logger.warn(
                "Class Name: {}, Method Name: {}, Elapsed Time: {}ms",
                className, methodName, elapsedTime
            )
            logger.error("Error: {}", exception.message)
            throw exception
        }
    }
}