package resources;

public enum Resources {
    users("/users"),
    posts("/posts"),
    comments("/comments"),
    todos("/todos");


    private String resource;

    Resources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
