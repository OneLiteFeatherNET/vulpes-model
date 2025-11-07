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

    public ItemEnchantmentEntity() {

    }

    public ItemEnchantmentEntity(
            UUID id,
            String name,
            short level,
            ItemEntity item
    ) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public ItemEntity getItem() {
        return item;
    }

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
