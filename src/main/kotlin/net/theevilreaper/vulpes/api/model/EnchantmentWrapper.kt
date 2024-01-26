package net.theevilreaper.vulpes.api.model

@Deprecated(
    "This class is deprecated and will be removed in the future",
    replaceWith = ReplaceWith("Use the Enchantment object from the server instead")
)
data class EnchantmentWrapper(
    val mojangName: String,
    val minLevel: Int = 1,
    val maxLevel: Int,
    val category: String
) : VulpesModel {

    private val minecraftRegex = Regex("minecraft:")

    fun toVariableName(): String = mojangName.replace(minecraftRegex, "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EnchantmentWrapper

        return mojangName == other.mojangName
    }

    override fun hashCode(): Int {
        return mojangName.hashCode()
    }
}
