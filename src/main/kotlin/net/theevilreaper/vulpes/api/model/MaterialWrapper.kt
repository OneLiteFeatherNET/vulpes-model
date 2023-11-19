package net.theevilreaper.vulpes.api.model

import net.theevilreaper.vulpes.api.model.VulpesModel

data class MaterialWrapper(
    val id: Int,
    val mojangName: String,
    val rarity: String,
    val translationKey: String,
    val depletes: Boolean,
    val maxStackSize: Int,
    val maxDamage: Int,
    val edible: Boolean,
    val fireResistant: Boolean,
    val blockId: String,
    val eatingSound: String,
    val drinkingSound: String,
) : VulpesModel
