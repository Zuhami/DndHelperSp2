package app.entities;

public class Monster {
    private Long id;
    private String name;
    private int armorClass;
    private int hitPoints;
    private String description;
    private String size;

    public Monster(String name, int armorClass, int hitPoints, String description, String size) {
        this.name = name;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.description = description;
        this.size = size;
        }
    }
