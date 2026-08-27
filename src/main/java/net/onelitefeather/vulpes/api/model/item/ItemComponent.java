package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.ItemEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity(name = "item_component")
public class ItemComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemEntity item;

    @NotNull
    private String identifier;
    private String value;

    public ItemComponent() {
        // No-argument constructor for JPA
    }

    public ItemComponent(UUID id) {
        this.id = id;
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
}
