package net.onelitefeather.vulpes.api.model.dimension;

/**
 * Represents the key of a known environment attribute that can be attached to a
 * {@link DimensionTypeEntity} via {@link DimensionAttributeEntity}.
 * <p>
 * Mirrors the constants declared in {@code net.minestom.server.world.attribute.EnvironmentAttributes}.
 * Unlike a dimension type itself, environment attributes are not a runtime-dynamic registry - they are
 * a fixed set of constants compiled into Minestom, which is why they are modelled here as an enum
 * instead of a free-form string. Keeping this enum in sync with Minestom's attribute set is a manual
 * step when that dependency is upgraded.
 * </p>
 */
public enum EnvironmentAttributeKey {
    FOG_COLOR("visual/fog_color"),
    FOG_START_DISTANCE("visual/fog_start_distance"),
    FOG_END_DISTANCE("visual/fog_end_distance"),
    SKY_FOG_END_DISTANCE("visual/sky_fog_end_distance"),
    CLOUD_FOG_END_DISTANCE("visual/cloud_fog_end_distance"),
    WATER_FOG_COLOR("visual/water_fog_color"),
    WATER_FOG_START_DISTANCE("visual/water_fog_start_distance"),
    WATER_FOG_END_DISTANCE("visual/water_fog_end_distance"),
    SKY_COLOR("visual/sky_color"),
    SUNRISE_SUNSET_COLOR("visual/sunrise_sunset_color"),
    CLOUD_COLOR("visual/cloud_color"),
    CLOUD_HEIGHT("visual/cloud_height"),
    SUN_ANGLE("visual/sun_angle"),
    MOON_ANGLE("visual/moon_angle"),
    STAR_ANGLE("visual/star_angle"),
    MOON_PHASE("visual/moon_phase"),
    STAR_BRIGHTNESS("visual/star_brightness"),
    BLOCK_LIGHT_TINT("visual/block_light_tint"),
    SKY_LIGHT_COLOR("visual/sky_light_color"),
    SKY_LIGHT_FACTOR("visual/sky_light_factor"),
    NIGHT_VISION_COLOR("visual/night_vision_color"),
    AMBIENT_LIGHT_COLOR("visual/ambient_light_color"),
    DEFAULT_DRIPSTONE_PARTICLE("visual/default_dripstone_particle"),
    AMBIENT_PARTICLES("visual/ambient_particles"),
    BACKGROUND_MUSIC("audio/background_music"),
    MUSIC_VOLUME("audio/music_volume"),
    AMBIENT_SOUNDS("audio/ambient_sounds"),
    FIREFLY_BUSH_SOUNDS("audio/firefly_bush_sounds"),
    SKY_LIGHT_LEVEL("gameplay/sky_light_level"),
    CAN_START_RAID("gameplay/can_start_raid"),
    WATER_EVAPORATES("gameplay/water_evaporates"),
    BED_RULE("gameplay/bed_rule"),
    RESPAWN_ANCHOR_WORKS("gameplay/respawn_anchor_works"),
    NETHER_PORTAL_SPAWNS_PIGLINS("gameplay/nether_portal_spawns_piglin"),
    FAST_LAVA("gameplay/fast_lava"),
    INCREASED_FIRE_BURNOUT("gameplay/increased_fire_burnout"),
    EYEBLOSSOM_OPEN("gameplay/eyeblossom_open"),
    TURTLE_EGG_HATCH_CHANCE("gameplay/turtle_egg_hatch_chance"),
    PIGLINS_ZOMBIFY("gameplay/piglins_zombify"),
    SNOW_GOLEM_MELTS("gameplay/snow_golem_melts"),
    CREAKING_ACTIVE("gameplay/creaking_active"),
    SURFACE_SLIME_SPAWN_CHANCE("gameplay/surface_slime_spawn_chance"),
    CAT_WAKING_UP_GIFT_CHANCE("gameplay/cat_waking_up_gift_chance"),
    BEES_STAY_IN_HIVE("gameplay/bees_stay_in_hive"),
    MONSTERS_BURN("gameplay/monsters_burn"),
    CAN_PILLAGER_PATROL_SPAWN("gameplay/can_pillager_patrol_spawn"),
    VILLAGER_ACTIVITY("gameplay/villager_activity"),
    BABY_VILLAGER_ACTIVITY("gameplay/baby_villager_activity");

    private final String key;

    EnvironmentAttributeKey(String key) {
        this.key = key;
    }

    /**
     * Returns the registry key of this environment attribute (e.g. {@code "visual/fog_color"}).
     *
     * @return the registry key
     */
    public String key() {
        return key;
    }
}
