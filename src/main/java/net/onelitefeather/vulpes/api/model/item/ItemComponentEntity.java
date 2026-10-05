package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.ItemEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

/**
 * A data component which is set on an item, e.g. {@code minecraft:food}.
 * <p>
 * The value is stored in the vanilla JSON format of the component, so it can be read with the codec of the
 * component when the item is generated. Components with a dedicated field on the {@link ItemEntity}, like the
 * lore or the enchantments, are not stored here.
 * </p>
 */
@Entity(name = "item_components")
@Table(name = "item_components", indexes = {
        @Index(name = "idx_item_components_item_key", columnList = "item_id, component_key", unique = true)
})
public final class ItemComponentEntity extends IdentifiableEntity {

    @NotNull
    private String componentKey;

    @JdbcTypeCode(SqlTypes.JSON)
    private String componentValue;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemEntity item;

    public ItemComponentEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ItemComponentEntity} with the specified values.
     *
     * @param id             the unique identifier of the component entry
     * @param componentKey   the key of the data component, e.g. {@code minecraft:food}
     * @param componentValue the value in the vanilla JSON format, null for components without a value
     */
    public ItemComponentEntity(UUID id, String componentKey, String componentValue) {
        this.setId(id);
        this.componentKey = componentKey;
        this.componentValue = componentValue;
    }

    /**
     * Returns the key of the data component.
     *
     * @return the key, e.g. {@code minecraft:food}
     */
    public String getComponentKey() {
        return componentKey;
    }

    /**
     * Sets the key of the data component.
     *
     * @param componentKey the key to set
     */
    public void setComponentKey(String componentKey) {
        this.componentKey = componentKey;
    }

    /**
     * Returns the value of the component in the vanilla JSON format.
     *
     * @return the value, e.g. {@code {"nutrition":4,"saturation":2.4}}, or null for components without a value
     */
    public String getComponentValue() {
        return componentValue;
    }

    /**
     * Sets the value of the component in the vanilla JSON format.
     *
     * @param componentValue the value to set
     */
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    /**
     * Returns the {@link ItemEntity} which is linked with this component.
     *
     * @return the item associated with this component
     */
    public ItemEntity getItem() {
        return item;
    }

    /**
     * Sets the item associated with this component.
     *
     * @param item the item to associate with this component
     */
    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemComponentEntity) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.componentKey, that.componentKey) &&
                Objects.equals(this.componentValue, that.componentValue) &&
                Objects.equals(itemId(), that.itemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), componentKey, componentValue, itemId());
    }

    // Only the id of the item, its toString and equals contain the components again.
    private UUID itemId() {
        return item == null ? null : item.getId();
    }

    @Override
    public String toString() {
        return "ItemComponentEntity[" +
                "id=" + getId() + ", " +
                "componentKey=" + componentKey + ", " +
                "componentValue=" + componentValue + ", " +
                "item=" + itemId() + ']';
    }
}
