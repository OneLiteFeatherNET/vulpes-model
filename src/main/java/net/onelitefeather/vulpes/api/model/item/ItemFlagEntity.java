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
 */
@Entity(name = "item_flags")
public final class ItemFlagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    /**
     * @param id   the unique identifier of the item flag
     * @param text the descriptive text of the item flag
     */
    public ItemFlagEntity(
            UUID id,
            String text,
            ItemEntity item
    ) {
        this.id = id;
        this.text = text;
        this.item = item;
    }

    public ItemFlagEntity() {

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public ItemEntity getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemFlagEntity) obj;
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
        return "ItemFlagEntity[" +
                "id=" + id + ", " +
                "text=" + text + ", " +
                "item=" + item + ']';
    }


}
