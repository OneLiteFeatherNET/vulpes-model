package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.item.ItemEnchantmentEntity;
import net.onelitefeather.vulpes.api.model.item.ItemFlagEntity;
import net.onelitefeather.vulpes.api.model.item.ItemLoreEntity;

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
public class ItemEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    private String uiName;
    private String variableName;
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
     * @param variableName    the name of the item
     * @param comment         a description of the item
     * @param displayName     the display name of the item
     * @param material        the material type associated with the item
     * @param groupName       the group to which the item belongs
     * @param customModelData the custom model data for the item
     * @param amount          the amount of the item
     * @param enchantments    the enchantments applied to the item
     * @param lore            the lore associated with the item
     * @param flags           the flags associated with the item
     */
    public ItemEntity(
            UUID id,
            String uiName,
            String variableName,
            String comment,
            String displayName,
            String material,
            String groupName,
            int customModelData,
            int amount,
            List<ItemEnchantmentEntity> enchantments,
            List<ItemLoreEntity> lore,
            List<ItemFlagEntity> flags
    ) {
        this.id = id;
        this.uiName = uiName;
        this.variableName = variableName;
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
     * Returns the unique identifier of the item.
     *
     * @return the unique identifier of the item
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the item.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

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
     * Sets the variable name for the notification
     *
     * @param variableName the variable name to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Returns the variable name for the notification
     *
     * @return the variable name of the notification
     */
    public String getVariableName() {
        return variableName;
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
                "id='" + id + '\'' +
                ", modelName='" + uiName + '\'' +
                ", name='" + variableName + '\'' +
                ", comment='" + comment + '\'' +
                ", displayName='" + displayName + '\'' +
                ", material='" + material + '\'' +
                ", group='" + groupName + '\'' +
                ", customModelData=" + customModelData +
                ", amount=" + amount +
                ", enchantments=" + enchantments +
                ", lore=" + lore +
                ", flags=" + flags +
                '}';
    }
}
