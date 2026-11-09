package net.onelitefeather.vulpes.api.model.asset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssetKeySpaceTest {

    @Test
    @DisplayName("a texture lives in the texture key space")
    void textureLivesInTextureKeySpace() {
        assertEquals(AssetKeySpace.TEXTURE, AssetKeySpace.of(AssetKind.TEXTURE));
    }

    @Test
    @DisplayName("Minecraft models and Blockbench models share the model key space")
    void modelsShareModelKeySpace() {
        assertEquals(AssetKeySpace.MODEL, AssetKeySpace.of(AssetKind.MODEL));
        assertEquals(AssetKeySpace.MODEL, AssetKeySpace.of(AssetKind.BBMODEL));
    }

    @Test
    @DisplayName("a sound lives in the sound key space")
    void soundLivesInSoundKeySpace() {
        assertEquals(AssetKeySpace.SOUND, AssetKeySpace.of(AssetKind.SOUND));
    }

    @Test
    @DisplayName("a sound is stored as Ogg audio")
    void soundIsStoredAsOgg() {
        assertEquals("audio/ogg", AssetKind.SOUND.contentType());
    }
}
