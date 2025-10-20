package app.dtos;

import app.entities.PlaceholderEntity; // TODO: Ret import til din egen entity
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PlaceholderDTO { // TODO: Ret klassenavnet (fx HotelDTO -> CustomerDTO)
    // TODO: Ret felter så de matcher din entity
    private Integer id;
    private String name;
    private String address;
    private PlaceholderEntity.PlaceholderType type; // TODO: Ret enum-type eller fjern hvis irrelevant
    private Set<PlaceholderChildDTO> children = new HashSet<>(); // TODO: Ret til korrekt child DTO-type (fx RoomDTO)

    // TODO: Lav konstruktør, der modtager din entity
    public PlaceholderDTO(PlaceholderEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();           // TODO: Ret til korrekte getters
        this.address = entity.getAddress();     // TODO: Ret til korrekte getters
        this.type = entity.getType();           // TODO: Ret/fjern efter behov
        if (entity.getChildren() != null) {     // TODO: Ret navnet til dit relationsfelt
            entity.getChildren().forEach(child -> children.add(new PlaceholderChildDTO(child))); // TODO: Ret ChildDTO
        }
    }

    // TODO: Evt. intern konstruktør (hvis du skal oprette DTO manuelt)
    public PlaceholderDTO(String name, String address, PlaceholderEntity.PlaceholderType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    // TODO: Ret statisk metode til din egen type
    public static List<PlaceholderDTO> toDTOList(List<PlaceholderEntity> entities) {
        return entities.stream().map(PlaceholderDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaceholderDTO dto)) return false;
        return getId().equals(dto.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}