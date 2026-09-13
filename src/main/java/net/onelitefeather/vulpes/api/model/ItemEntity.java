package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.item.ItemEnchantmentEntity;
import net.onelitefeather.vulpes.api.model.item.ItemFlagEntity;
import net.onelitefeather.vulpes.api.model.item.ItemLoreEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.List;
import java.util.UUID;

/**
 * Represents an Item in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to an item such as name, description,
 * material, enchantments, etc.
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
    private String displayName;
    private String material;
    private String groupName;
    private int customModelData;
    private int amount;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemEnchantmentEntity> enchantments;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemLoreEntity> lore;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemFlagEntity> flags;

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
     * @param displayName     the display name of the item
     * @param material        the material type associated with the item
     * @param groupName       the group to which the item belongs
     * @param customModelData the custom model data for the item
     * @param amount          the amount of the item
     * @param enchantments    the enchantments applied to the item
     * @param lore            the lore associated with the item
     * @param flags           the flags associated with the item
     * @param project         the project this item belongs to
     */
    public ItemEntity(
            UUID id,
            String uiName,
            String key,
            String comment,
            String displayName,
            String material,
            String groupName,
            int customModelData,
            int amount,
            List<ItemEnchantmentEntity> enchantments,
            List<ItemLoreEntity> lore,
            List<ItemFlagEntity> flags,
            ProjectEntity project
    ) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.comment = comment;
        this.displayName = displayName;
        this.material = material;
        this.groupName = groupName;
        this.customModelData = customModelData;
        this.amount = amount;
        this.enchantments = enchantments;
        this.lore = lore;
        this.flags = flags;
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
     * Returns the display name of the item.
     *
     * @return the display name of the item
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name of the item.
     *
     * @param displayName the display name to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the material type associated with the item.
     *
     * @return the material type of the item
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material type associated with the item.
     *
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
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
     * Returns the custom model data for the item.
     *
     * @return the custom model data of the item
     */
    public int getCustomModelData() {
        return customModelData;
    }

    /**
     * Sets the custom model data for the item.
     *
     * @param customModelData the custom model data to set
     */
    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    /**
     * Returns the amount of the item.
     *
     * @return the amount of the item
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the item.
     *
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
     * Returns the flags associated with the item.
     *
     * @return the flags of the item
     */
    public List<ItemFlagEntity> getFlags() {
        return flags;
    }

    /**
     * Sets the flags associated with the item.
     *
     * @param flags the flags to set
     */
    public void setFlags(List<ItemFlagEntity> flags) {
        this.flags = flags;
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
                ", displayName='" + displayName + '\'' +
                ", material='" + material + '\'' +
                ", group='" + groupName + '\'' +
                ", customModelData=" + customModelData +
                ", amount=" + amount +
                ", enchantments=" + enchantments +
                ", lore=" + lore +
                ", flags=" + flags +
                ", project=" + getProject() +
                '}';
    }
}
