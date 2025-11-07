package net.onelitefeather.vulpes.api.model.font;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "font_lore")
public final class FontLoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private  String line;
    @ManyToOne
    @JoinColumn(name = "font_id", nullable = false)
    private  FontEntity font;

    public FontLoreEntity() {
    }

    public FontLoreEntity(
            UUID id,
            String line,
            FontEntity font
    ) {
        this.id = id;
        this.line = line;
        this.font = font;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setFont(FontEntity font) {
        this.font = font;
    }

    public FontEntity getFont() {
        return font;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FontLoreEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.line, that.line) &&
                Objects.equals(this.font, that.font);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, line, font);
    }

    @Override
    public String toString() {
        return "FontLoreEntity[" +
                "id=" + id + ", " +
                "line=" + line + ", " +
                "font=" + font + ']';
    }

}
