//====================================
//	Kyle Russell
//	jdamvc
//	Attribute
//====================================

package jdamvc.engine.core.database.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute
{
    String name() default "";
    
    boolean isPrimaryKey() default false;
}