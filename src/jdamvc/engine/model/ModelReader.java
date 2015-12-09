package jdamvc.engine.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import jdamvc.engine.core.database.mapping.Attribute;
import jdamvc.engine.core.database.mapping.Entity;

public class ModelReader
{
    private static Map<String, Model> models;
    
    private static Annotation[] getAnnotationsForModal(Model model)
    {
        return model.getClass().getAnnotations();
    }
    
    public static Entity getEntityName(Model model)
    {
        Annotation entity = model.getClass().getAnnotation(Entity.class);
        if(entity != null) return (Entity) entity;
        else return null;
    }
    
    public static Map<String, Attribute> getColumns(Model model)
    {
        Map<String, Attribute> attrs = new HashMap<>();
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field field : fields)
        {
            Annotation annotation = field.getDeclaredAnnotation(Attribute.class);
            if(annotation != null)
            {
                Attribute attr = (Attribute) annotation;
                if(attr.name().equals(""))
                    attrs.put(field.getName(), attr);
                else
                    attrs.put(attr.name(), attr);
            }
        }
        
        return attrs;
    }
    
    
    public static Model getModel(String tableName)
    {
        return models.get(tableName);
    }
}
