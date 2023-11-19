package net.theevilreaper.vulpes.api.util

import net.theevilreaper.vulpes.api.model.NotificationModel


/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/

fun NotificationModel.hasDescription(): Boolean {
    return description.isNullOrEmpty()
}

fun NotificationModel.hasTitle(): Boolean {
    return title.isNullOrEmpty()
}