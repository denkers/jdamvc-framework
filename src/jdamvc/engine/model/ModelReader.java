
package jdamvc.engine.model;

import java.util.Map;

public class ModelReader
{
    private static Map<String, Model> models;
    
    public static Model getModel(String tableName)
    {
        return models.get(tableName);
    }
}
