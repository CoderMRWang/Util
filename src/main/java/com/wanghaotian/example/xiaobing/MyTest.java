package com.wanghaotian.example.xiaobing;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

/**
 * author;Wanghaotian
 * data:2020/4/17 0017
 */
public class MyTest extends ClassPathBeanDefinitionScanner {
    public MyTest(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public MyTest(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }
}
