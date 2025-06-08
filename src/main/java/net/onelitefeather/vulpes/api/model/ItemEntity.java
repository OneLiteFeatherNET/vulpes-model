package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.util.List;
import java.util.Map;
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

    private String modelName;
    private String name;
    private String description;
    private String displayName;
    private String material;
    private String groupName;
    private int customModelData;
    private int amount;
    @ElementCollection
    private Map<String, Short> enchantments;
    @ElementCollection
    private List<String> lore;
    @ElementCollection
    private List<String> flags;

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
     * @param modelName       the model name associated with the item
     * @param name            the name of the item
     * @param description     a description of the item
     * @param displayName     the display name of the item
     * @param material        the material type associated with the item
     * @param groupName           the group to which the item belongs
     * @param customModelData the custom model data for the item
     * @param amount          the amount of the item
     * @param enchantments    the enchantments applied to the item
     * @param lore            the lore associated with the item
     * @param flags           the flags associated with the item
     */
    public ItemEntity(UUID id, String modelName, String name, String description, String displayName, String material, String groupName, int customModelData, int amount, Map<String, Short> enchantments, List<String> lore, List<String> flags) {
        this.id = id;
        this.modelName = modelName;
        this.name = name;
        this.description = description;
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
     * Returns the model name associated with the item.
     *
     * @return the model name of the item
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the model name associated with the item.
     *
     * @param modelName the model name to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
    public Map<String, Short> getEnchantments() {
        return enchantments;
    }

    /**
     * Sets the enchantments applied to the item.
     *
     * @param enchantments the enchantments to set
     */
    public void setEnchantments(Map<String, Short> enchantments) {
        this.enchantments = enchantments;
    }

    /**
     * Returns the lore associated with the item.
     *
     * @return the lore of the item
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * Sets the lore associated with the item.
     *
     * @param lore the lore to set
     */
    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    /**
     * Returns the flags associated with the item.
     *
     * @return the flags of the item
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Sets the flags associated with the item.
     *
     * @param flags the flags to set
     */
    public void setFlags(List<String> flags) {
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
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
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
