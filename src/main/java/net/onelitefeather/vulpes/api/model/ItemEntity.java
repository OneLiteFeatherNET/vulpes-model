package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.item.ItemComponentEntity;
import net.onelitefeather.vulpes.api.model.item.ItemEnchantmentEntity;
import net.onelitefeather.vulpes.api.model.item.ItemLoreEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.List;
import java.util.UUID;

/**
 * Represents an Item in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to an item such as name, description,
 * enchantments, etc. Everything else about the item stack is a data component: the vanilla ones like
 * the custom name, and Stelaris' own ones for the material and the amount, see
 * {@link net.onelitefeather.vulpes.api.model.item.StelarisComponents}.
 * <p>
 * This class is mapped to the database table "items" and contains fields that are automatically
 * persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "items")
@Table(name = "items", indexes = {
        @Index(name = "idx_items_project_id", columnList = "project_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_items_project_key", columnNames = {"project_id", "key"})
})
public class ItemEntity extends AbstractEntity {

    private String uiName;
    private String comment;
    private String groupName;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemEnchantmentEntity> enchantments;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemLoreEntity> lore;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemComponentEntity> components;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public ItemEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ItemEntity} with the specified values.
     *
     * @param id              the unique identifier of the item
     * @param uiName          the model name associated with the item
     * @param key             the local key of the item within the project's namespace
     *                        (e.g. {@code dirt}, becomes {@code <project-key>:dirt}
     *                        via {@link #getNamespacedKey()})
     * @param comment         a description of the item
     * @param groupName       the group to which the item belongs
     * @param enchantments    the enchantments applied to the item
     * @param lore            the lore associated with the item
     * @param project         the project this item belongs to
     */
    public ItemEntity(
            UUID id,
            String uiName,
            String key,
            String comment,
            String groupName,
            List<ItemEnchantmentEntity> enchantments,
            List<ItemLoreEntity> lore,
            ProjectEntity project
    ) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.comment = comment;
        this.groupName = groupName;
        this.enchantments = enchantments;
        this.lore = lore;
    }

    // Getters and setters for each field

    /**
     * Sets the name representation for the ui
     *
     * @param uiName the name to set for the ui
     */
    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * Returns the name representation for the ui
     *
     * @return the given ui name
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Returns the comment of the notification
     *
     * @return the description
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the notification
     *
     * @param description the comment to set
     */
    public void setComment(String description) {
        this.comment = description;
    }

    /**
     * Returns the group to which the item belongs.
     *
     * @return the group of the item
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the group to which the item belongs.
     *
     * @param group the group to set
     */
    public void setGroupName(String group) {
        this.groupName = group;
    }

    /**
     * Returns the enchantments applied to the item.
     *
     * @return the enchantments of the item
     */
    public List<ItemEnchantmentEntity> getEnchantments() {
        return enchantments;
    }

    /**
     * Sets the enchantments applied to the item.
     *
     * @param enchantments the enchantments to set
     */
    public void setEnchantments(List<ItemEnchantmentEntity> enchantments) {
        this.enchantments = enchantments;
    }

    /**
     * Returns the lore associated with the item.
     *
     * @return the lore of the item
     */
    public List<ItemLoreEntity> getLore() {
        return lore;
    }

    /**
     * Sets the lore associated with the item.
     *
     * @param lore the lore to set
     */
    public void setLore(List<ItemLoreEntity> lore) {
        this.lore = lore;
    }

    /**
     * Returns the data components of the item which have no dedicated field.
     *
     * @return the components of the item
     */
    public List<ItemComponentEntity> getComponents() {
        return components;
    }

    /**
     * Sets the data components of the item which have no dedicated field.
     *
     * @param components the components to set
     */
    public void setComponents(List<ItemComponentEntity> components) {
        this.components = components;
    }

    /**
     * Provides a string representation of the ItemModel.
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "ItemModel{" +
                "id='" + getId() + '\'' +
                ", modelName='" + uiName + '\'' +
                ", key='" + getKey() + '\'' +
                ", comment='" + comment + '\'' +
                ", group='" + groupName + '\'' +
                ", enchantments=" + enchantments +
                ", lore=" + lore +
                ", components=" + components +
                ", project=" + getProject() +
                '}';
    }
}
