package br.com.fornaro.githubapis.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}