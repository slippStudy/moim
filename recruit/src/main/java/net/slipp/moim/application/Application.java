package net.slipp.moim.application;


import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Transactional
public @interface Application {


    /**
     * Alias for {@link RequestMapping#name}.
     */
    @AliasFor(annotation = Transactional.class)
    boolean readOnly() default false;
}
