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

/**
 * The {@link ItemFlagEntity} represents a flag or attribute that can be associated with an item in the system.
 *
 * @author theEvilReaper
 * @version 1.1.0
 * @since 1.6.0
 */
@Entity(name = "item_flags")
public final class ItemFlagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String flag;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    public ItemFlagEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ItemFlagEntity} with the specified parameters.
     *
     * @param id   the unique identifier of the item flag
     * @param flag the descriptive text of the item flag
     */
    public ItemFlagEntity(
            UUID id,
            String flag
    ) {
        this.id = id;
        this.flag = flag;
    }

    /**
     * Sets the unique identifier of the item flag.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the item flag.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the flag associated with the item.
     *
     * @param flag the item flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * Gets the flag associated with the item.
     *
     * @return the item flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the item associated with this flag.
     *
     * @param item the item to associate
     */
    public void setItem(ItemEntity item) {
        this.item = item;
    }

    /**
     * Gets the item associated with this flag.
     *
     * @return the associated item
     */
    public ItemEntity getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemFlagEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.flag, that.flag) &&
                Objects.equals(this.item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flag, item);
    }

    @Override
    public String toString() {
        return "ItemFlagEntity[" +
                "id=" + id + ", " +
                "text=" + flag + ", " +
                "item=" + item + ']';
    }
}
