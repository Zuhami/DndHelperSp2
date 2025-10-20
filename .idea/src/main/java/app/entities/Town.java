package app.entities;

public class Town {
    private Long id;
    private String name;
    private String description;
    private String features;

    public Town(String name, String description, String features) {
        this.name = name;
        this.description = description;
        this.features = features;
    }
}
