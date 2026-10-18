package net.onelitefeather.vulpes.api.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttributeEntityTest {

    @Test
    void fullConstructorKeepsAllValues() {
        AttributeEntity entity = new AttributeEntity(UUID.randomUUID(), "UI name", "max_health", 20, 1, 1024,
                true, "attribute.name.max_health", "comment", null);
        assertEquals(20, entity.getDefaultValue(), "The default value given to the constructor must be returned");
        assertEquals(1, entity.getMinimumValue(), "The minimum value given to the constructor must be returned");
        assertEquals(1024, entity.getMaximumValue(), "The maximum value given to the constructor must be returned");
        assertTrue(entity.isSynced(), "The sync flag given to the constructor must be returned");
        assertEquals("attribute.name.max_health", entity.getTranslationKey(),
                "The translation key given to the constructor must be returned");
    }

    @Test
    void shortConstructorUsesDefaultsForNewValues() {
        AttributeEntity entity = new AttributeEntity(UUID.randomUUID(), "UI name", "max_health", 20, 1024, "comment", null);
        assertEquals(0, entity.getMinimumValue(), "Without a minimum value the minimum must be 0");
        assertFalse(entity.isSynced(), "Without a sync flag the attribute must not be synced");
        assertNull(entity.getTranslationKey(), "Without a translation key the key must be null");
    }

    @Test
    void defaultConstructorIsNotSynced() {
        assertFalse(new AttributeEntity().isSynced(), "A new entity must not be synced by default");
    }

    @Test
    void settersChangeNewValues() {
        AttributeEntity entity = new AttributeEntity();
        entity.setMinimumValue(-1);
        entity.setSynced(true);
        entity.setTranslationKey("attribute.name.armor");
        assertEquals(-1, entity.getMinimumValue(), "setMinimumValue must change the minimum value");
        assertTrue(entity.isSynced(), "setSynced(true) must change the flag");
        assertEquals("attribute.name.armor", entity.getTranslationKey(), "setTranslationKey must change the key");
    }
}
