//====================================
//	Kyle Russell
//	jdamvc
//	ModelReader
//====================================

package jdamvc.engine.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import jdamvc.engine.core.database.Column;
import jdamvc.engine.core.database.mapping.Attribute;
import jdamvc.engine.core.database.mapping.Entity;

public class ModelReader
{
    
    public static Entity getEntityName(Model model)
    {
        Annotation entity = model.getClass().getAnnotation(Entity.class);
        if(entity != null) 
            return (Entity) entity;
        else 
            return null;
    }
    
    public static Map<String, Column> getColumns(Model model)
    {
        Map<String, Column> attrs   =   new HashMap<>();
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field field : fields)
        {
            Annotation annotation = field.getDeclaredAnnotation(Attribute.class);
            if(annotation != null)
            {
                Class<?> type   =   field.getType();
                String name;
                
                Attribute attr  =   (Attribute) annotation;
                if(attr.name().equals(""))
                    name    =   field.getName();
                else
                    name    =   attr.name();
                
                Column col  =   new Column(name, type);
                attrs.put(name, col);
            }
        }
        
        return attrs;
    }
}
