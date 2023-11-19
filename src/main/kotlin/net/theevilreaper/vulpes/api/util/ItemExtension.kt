package net.theevilreaper.vulpes.api.util

import net.theevilreaper.vulpes.api.model.ItemModel


/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/

private const val MAX_ITEM_SIZE = 64

fun ItemModel.getAmount(): Int? {
    return if (amount != null && amount > MAX_ITEM_SIZE) MAX_ITEM_SIZE else amount
}

fun ItemModel.hasMaterial(): Boolean {
    return material.isNullOrEmpty() && hasValidNameSpace(material)
}

fun ItemModel.hasDescription(): Boolean {
    return description.isNullOrEmpty() && hasValidNameSpace(description)
}

fun ItemModel.hasDisplayName(): Boolean {
    return displayName.isNullOrEmpty()
}

fun ItemModel.hasEnchantments(): Boolean {
    return enchantments.isNullOrEmpty()
}

fun ItemModel.hasLoreLines(): Boolean {
    return !lore.isNullOrEmpty()
}

fun ItemModel.hasFlags(): Boolean {
    return !flags.isNullOrEmpty()
}

fun ItemModel.hasMetaData(): Boolean {
    return hasDisplayName() && hasEnchantments() && hasFlags() && hasLoreLines()
}
    