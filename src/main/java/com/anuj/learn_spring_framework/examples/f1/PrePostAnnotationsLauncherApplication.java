package com.anuj.learn_spring_framework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass {
    private SomeDep someDep;

    public SomeClass(SomeDep someDep) {
        this.someDep = someDep;
        System.out.println("All deps are ready");
    }

    @PostConstruct
    public void initalize() {
        someDep.getReady();
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleanup~");
    }
}

@Component
class SomeDep {

    public void getReady() {
        System.out.println("Some logic using somedep");
    }
}

@Configuration
@ComponentScan
public class PrePostAnnotationsLauncherApplication {
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(PrePostAnnotationsLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
