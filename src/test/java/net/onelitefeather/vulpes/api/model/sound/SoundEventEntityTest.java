package net.onelitefeather.vulpes.api.model.sound;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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
}
