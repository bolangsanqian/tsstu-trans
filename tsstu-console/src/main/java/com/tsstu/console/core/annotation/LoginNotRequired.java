package com.tsstu.console.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface LoginNotRequired {
	
}
