package net.theevilreaper.vulpes.api.util

import net.theevilreaper.vulpes.api.model.VulpesModel

/**
 * Checks if a given [String] contains the namespace from the game Minecraft-
 * @param name the string to check
 */
fun VulpesModel.hasValidNameSpace(name: String?): Boolean {
    return name.orEmpty().startsWith("minecraft:")
}