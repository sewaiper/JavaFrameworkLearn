package framework.objects;

public class GenericObjectDefinition implements ObjectDefinition {
    private String name;
    private Class<?> type;

    public GenericObjectDefinition(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return null;
    }
}
