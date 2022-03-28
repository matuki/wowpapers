package com.pmatuki.wowpapers.view.common

import android.content.Context
import android.widget.Toast
import timber.log.Timber

fun showError(
    context: Context,
    messageRes: Int,
    length: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, context.getString(messageRes), length).show()
}
