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
 */
@Entity(name = "item_lore")
public final class ItemLoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;


    public ItemLoreEntity() {
    }

    /**
     * @param id   the unique identifier of the lore entry
     * @param text the lore text
     */
    public ItemLoreEntity(
            UUID id,
            String text,
            ItemEntity item
    ) {
        this.id = id;
        this.text = text;
        this.item = item;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
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

}
