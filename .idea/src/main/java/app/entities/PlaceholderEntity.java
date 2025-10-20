package app.entities;

import app.dtos.PlaceholderDTO; // TODO: Ret import til korrekt DTO
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "placeholder") // TODO: Ret tabelnavn (fx "hotel")
public class PlaceholderEntity { // TODO: Ret klassenavn (fx Hotel)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeholder_id", nullable = false, unique = true) // TODO: Ret kolonnenavn
    private Integer id;

    @Setter
    @Column(name = "placeholder_name", nullable = false, unique = true) // TODO: Ret navn
    private String name;

    @Setter
    @Column(name = "placeholder_address", nullable = false) // TODO: Ret navn/fjern hvis irrelevant
    private String address;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "placeholder_type", nullable = false) // TODO: Ret navn/fjern enum
    private PlaceholderType type;

    @OneToMany(mappedBy = "placeholderEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // TODO: Ret relation
    private Set<PlaceholderChildEntity> children = new HashSet<>(); // TODO: Ret navn (fx Set<Room> rooms)

    // TODO: Standard konstruktør
    public PlaceholderEntity(String name, String address, PlaceholderType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    // TODO: Konstruktør til konvertering fra DTO
    public PlaceholderEntity(PlaceholderDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.type = dto.getType();
        if (dto.getChildren() != null) { // TODO: Ret navn
            dto.getChildren().forEach(childDTO -> children.add(new PlaceholderChildEntity(childDTO))); // TODO: Ret child entity
        }
    }

    // TODO: Bi-directional relationship sætning
    public void setChildren(Set<PlaceholderChildEntity> children) { // TODO: Ret metode-navn
        if (children != null) {
            this.children = children;
            for (PlaceholderChildEntity child : children) {
                child.setParent(this); // TODO: Ret "setHotel" → "setParent" el. tilsvarende
            }
        }
    }

    // TODO: Bi-directional add-metode
    public void addChild(PlaceholderChildEntity child) { // TODO: Ret navn
        if (child != null) {
            this.children.add(child);
            child.setParent(this); // TODO: Ret "setHotel"
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceholderEntity that = (PlaceholderEntity) o;
        return Objects.equals(name, that.name); // TODO: Ret sammenligningsfelt
    }

    @Override
    public int hashCode() {
        return Objects.hash(name); // TODO: Ret felt
    }

    public enum PlaceholderType { // TODO: Ret enum (fx HotelType)
        TYPE1, TYPE2, TYPE3
    }
}
