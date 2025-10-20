package app.config;

import app.entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import java.util.Set;

public class PopulateDnD {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        Set<Monster> monsters = getMonsters();
        Set<Town> towns = getTowns();
        Set<NPC> npcs = getNpcs();
        Set<Shop> shops = getShops();
        Set<Action> actions = getActions();
        Set<User> users = getUsers();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // persist alle entities
            users.forEach(em::persist);
            monsters.forEach(em::persist);
            towns.forEach(em::persist);
            npcs.forEach(em::persist);
            shops.forEach(em::persist);
            actions.forEach(em::persist);

            em.getTransaction().commit();
        }
    }

    private static Set<User> getUsers() {
        User u1 = new User("Alice", "alice@example.com", "1234");
        User u2 = new User("Bob", "bob@example.com", "abcd");
        return Set.of(u1, u2);
    }

    private static Set<Monster> getMonsters() {
        Monster m1 = new Monster("Aboleth", 17, 135, "Ancient water creature", "Large");
        Monster m2 = new Monster("Goblin", 15, 7, "Sneaky and weak", "Small");
        return Set.of(m1, m2);
    }

    private static Set<Town> getTowns() {
        Town t1 = new Town("Riverville", "Small riverside town", "Mill, Dock, Market");
        Town t2 = new Town("Dragon's Rest", "Mountain town", "Inn, Blacksmith, Tavern");
        return Set.of(t1, t2);
    }

    private static Set<NPC> getNpcs() {
        NPC n1 = new NPC("Thorin", "Stout dwarf warrior", "Dwarf", "Warrior");
        NPC n2 = new NPC("Elara", "Mystical elf mage", "Elf", "Mage");
        return Set.of(n1, n2);
    }

    private static Set<Shop> getShops() {
        Shop s1 = new Shop("Potion Emporium", "Magic");
        Shop s2 = new Shop("Blacksmith's Forge", "Weapons");
        return Set.of(s1, s2);
    }

    private static Set<Action> getActions() {
        Action a1 = new Action("Fireball", "Explosive fire magic", 50);
        Action a2 = new Action("Slash", "Quick sword attack", 15);
        return Set.of(a1, a2);
    }
}
