package net.onelitefeather.vulpes.api.model.sound;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SoundEventEntityTest {

    private static SoundEventEntity createEntity(boolean replace) {
        return new SoundEventEntity(UUID.randomUUID(), "UI name", "ambient.cave", "key name", replace, "subtitle", List.of(), null);
    }

    @Test
    void isReplaceReturnsTrueWhenConstructedWithTrue() {
        assertTrue(createEntity(true).isReplace(), "The replace flag given to the constructor must be returned");
    }

    @Test
    void isReplaceReturnsFalseWhenConstructedWithFalse() {
        assertFalse(createEntity(false).isReplace(), "The replace flag given to the constructor must be returned");
    }

    @Test
    void isReplaceIsFalseForDefaultConstructor() {
        assertFalse(new SoundEventEntity().isReplace(), "A new entity must not replace by default");
    }

    @Test
    void setReplaceChangesFlagToTrue() {
        SoundEventEntity entity = createEntity(false);
        entity.setReplace(true);
        assertTrue(entity.isReplace(), "setReplace(true) must change the flag");
    }

    @Test
    void setReplaceChangesFlagToFalse() {
        SoundEventEntity entity = createEntity(true);
        entity.setReplace(false);
        assertFalse(entity.isReplace(), "setReplace(false) must change the flag");
    }

    @Test
    void sourceIsMasterByDefault() {
        assertEquals(SoundSource.MASTER, createEntity(false).getSource(), "A new entity must play in the master source");
    }

    @Test
    void minimumValueIsZeroByDefault() {
        assertEquals(0.0, createEntity(false).getMinimumValue(), "A new entity must have a minimum value of 0");
    }

    @Test
    void setSourceChangesSource() {
        SoundEventEntity entity = createEntity(false);
        entity.setSource(SoundSource.WEATHER);
        assertEquals(SoundSource.WEATHER, entity.getSource(), "setSource must change the source");
    }

    @Test
    void setMinimumValueChangesMinimumValue() {
        SoundEventEntity entity = createEntity(false);
        entity.setMinimumValue(0.5);
        assertEquals(0.5, entity.getMinimumValue(), "setMinimumValue must change the minimum value");
    }
}
