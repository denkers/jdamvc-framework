//====================================
//	Kyle Russell
//	jdamvc
//	ModelReader
//====================================

package jdamvc.engine.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import jdamvc.engine.core.database.Column;
import jdamvc.engine.core.database.mapping.Attribute;
import jdamvc.engine.core.database.mapping.Entity;

public class ModelReader
{
    
    public static String getEntityName(Class<?> model)
    {
        Annotation attr =   model.getClass().getAnnotation(Entity.class);
        if(attr != null) 
        {
            Entity entity   =   (Entity) attr;
            if(entity.name().equals(""))
                return model.getClass().getName();
            else
                return entity.name();
        }
        
        else return model.getClass().getName();
    }
    
    public static String getPrimaryKey(Class<?> model)
    {
        Field[] fields  =   model.getClass().getDeclaredFields();
        for(Field field : fields)
        {
            Annotation annotation   =   field.getDeclaredAnnotation(Attribute.class);
            if(annotation != null)
            {
                Attribute attr      =   (Attribute) annotation;
                if(attr.isPrimaryKey())
                {
                    if(attr.name().equals(""))
                        return field.getName();
                    else
                        return attr.name();
                }
            }
        }
        
        return null;
    }
    
    public static Map<String, Column> getColumns(Class<?> model)
    {
        Map<String, Column> attrs   =   new LinkedHashMap<>();
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
