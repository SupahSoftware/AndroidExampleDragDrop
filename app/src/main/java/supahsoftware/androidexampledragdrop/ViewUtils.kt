package supahsoftware.androidexampledragdrop

import android.content.Context

fun Int.dpToPx(context: Context) = (this * context.resources.displayMetrics.density + 0.5f).toInt()