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
public class NPC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Race race;
    //private String race;
    private String professions;

    public NPC(String name, String description,Race race,String professions) {
        this.name = name;
        this.description = description;
        this.race = race;
        this.professions = professions;
    }

    public enum Race { // TODO: Ret enum (fx HotelType)
        HUMAN,ELF,HALFELF,HALFLING,HALFORC,DWARF,GNOME,TIEFLING,DRAGONBORN
    }
}
