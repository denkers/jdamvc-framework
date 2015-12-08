
package jdamvc.engine.model;

import java.lang.annotation.Annotation;
import java.util.Map;

public class ModelReader
{
    private static Map<String, Model> models;
    
    private static Annotation[] getAnnotationsForModal(Model model)
    {
        return model.getClass().getAnnotations();
    }
    
    
    
    public static Model getModel(String tableName)
    {
        return models.get(tableName);
    }
}
