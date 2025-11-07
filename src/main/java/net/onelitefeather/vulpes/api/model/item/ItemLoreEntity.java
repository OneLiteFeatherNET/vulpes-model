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
 * The {@link ItemLoreEntity} represents a lore entry associated with an item.
 *
 * @author theEvilReaper
 * @version 1.1.0
 * @since 1.6.0
 */
@Entity(name = "item_lore")
public final class ItemLoreEntity implements Comparable<ItemLoreEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;
    private int orderIndex;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    public ItemLoreEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ItemLoreEntity} with the specified values.
     *
     * @param id   the unique identifier of the lore entry
     * @param text the lore text
     */
    public ItemLoreEntity(
            UUID id,
            String text,
            int orderIndex
    ) {
        this.id = id;
        this.text = text;
        this.orderIndex = orderIndex;
    }

    /**
     * Sets the unique identifier of the lore entry.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the lore entry.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the item associated with this lore entry.
     *
     * @param item the item to set
     */
    public void setItem(ItemEntity item) {
        this.item = item;
    }

    /**
     * Gets the item associated with this lore entry.
     *
     * @return the associated item
     */
    public ItemEntity getItem() {
        return item;
    }

    /**
     * Sets the lore text.
     *
     * @param text the lore text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the lore text.
     *
     * @return the lore text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the order index of the lore entry.
     *
     * @param orderIndex the order index to set
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * Gets the order index of the lore entry.
     *
     * @return the order index
     */
    public int getOrderIndex() {
        return orderIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemLoreEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.text, that.text) &&
                Objects.equals(this.item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, item);
    }

    @Override
    public String toString() {
        return "ItemLoreEntity[" +
                "id=" + id + ", " +
                "text=" + text + ", " +
                "item=" + item + ']';
    }

    @Override
    public int compareTo(ItemLoreEntity o) {
        return Integer.compare(this.orderIndex, o.orderIndex);
    }
}
