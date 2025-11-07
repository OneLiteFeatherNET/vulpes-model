package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.ItemEntity;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "item_enchantments")
public final class ItemEnchantmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String name;
    private short level;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    public ItemEnchantmentEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ItemEnchantmentEntity} with the specified values.
     *
     * @param id    the unique identifier of the item enchantment
     * @param name  the name of the enchantment
     * @param level the level of the enchantment
     */
    public ItemEnchantmentEntity(
            UUID id,
            String name,
            short level
    ) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    /**
     * Returns the unique identifier of the item enchantment.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the item enchantment.
     *
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the name of the enchantment.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the enchantment.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the level of the enchantment.
     *
     * @return the level
     */
    public short getLevel() {
        return level;
    }

    /**
     * Sets the level of the enchantment.
     *
     * @param level the level to set
     */
    public void setLevel(short level) {
        this.level = level;
    }

    /**
     * Returns the {@link ItemEntity} which is linked with this enchantment.
     *
     * @return the item associated with this enchantment
     */
    public ItemEntity getItem() {
        return item;
    }

    /**
     * Sets the item associated with this enchantment.
     *
     * @param item the item to associate with this enchantment
     */
    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemEnchantmentEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                this.level == that.level &&
                Objects.equals(this.item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, item);
    }

    @Override
    public String toString() {
        return "ItemEnchantmentEntity[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "level=" + level + ", " +
                "item=" + item + ']';
    }

}
