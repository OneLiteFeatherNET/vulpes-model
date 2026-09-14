package net.onelitefeather.vulpes.api.model.font;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.FontEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "font_string")
public final class FontStringEntity extends IdentifiableEntity implements Comparable<FontStringEntity> {
    private String line;
    @ManyToOne
    @JoinColumn(name = "font_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FontEntity font;
    private int orderIndex;

    /**
     * Default constructor for JPA.
     */
    public FontStringEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link FontStringEntity} with the specified values.
     *
     * @param id         the unique identifier of the font string
     * @param content    the content of the font string
     * @param orderIndex the order index of the font string
     */
    public FontStringEntity(
            UUID id,
            String content,
            int orderIndex
    ) {
        this.setId(id);
        this.line = content;
        this.orderIndex = orderIndex;
    }

    /**
     * Sets the content of the font string.
     *
     * @param line the content to set
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Gets the content of the font string.
     *
     * @return the content
     */
    public String getLine() {
        return line;
    }

    /**
     * Sets the font associated with this font string.
     *
     * @param font the font to set
     */
    public void setFont(FontEntity font) {
        this.font = font;
    }

    /**
     * Gets the font associated with this font string.
     *
     * @return the font
     */
    public FontEntity getFont() {
        return font;
    }

    /**
     * Sets the order index of the font string.
     *
     * @param orderIndex the order index to set
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * Gets the order index of the font string.
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
        var that = (FontStringEntity) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.line, that.line) &&
                Objects.equals(this.font, that.font);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), line, font);
    }

    @Override
    public String toString() {
        return "FontLoreEntity[" +
                "id=" + getId() + ", " +
                "line=" + line + ", " +
                "font=" + font + ']';
    }

    @Override
    public int compareTo(FontStringEntity o) {
        return Integer.compare(this.orderIndex, o.orderIndex);
    }
}
