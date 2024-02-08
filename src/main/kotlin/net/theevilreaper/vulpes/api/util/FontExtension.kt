package net.theevilreaper.vulpes.api.util

import net.theevilreaper.vulpes.api.model.FontModel


/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/
fun FontModel.hasFontSymbols(): Boolean {
    return chars.isNullOrEmpty()
}

fun FontModel.hasShiftData(): Boolean {
    return shift.isNullOrEmpty()
}

fun FontModel.getShiftAsArray(): Array<Double> {
    return shift!!.map { it }.toTypedArray()
}