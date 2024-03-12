package org.hplx.listeners;


import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (annotation != null) {
            try {
                annotation.setRetryAnalyzer(RetryFailedTests.class);
            } catch (Exception e) {
                // Handle exceptions if setting the retry analyzer fails
                e.printStackTrace();
            }
        }
    }
}






