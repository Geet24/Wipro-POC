package com.kotlin.mvvm.common.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentTransactionHelper {

    fun replaceFragment(
        manager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean = true
    ) {
        val transaction = manager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }
        transaction.replace(frameId, fragment, fragment::class.java.simpleName)
        transaction.commit()
    }

    fun popFragmentFromBackStack(
        fragmentManager: FragmentManager,
        tag: String?,
        isExclusive: Boolean = true
    ) {
        val flag = if (isExclusive) 0 else FragmentManager.POP_BACK_STACK_INCLUSIVE
        fragmentManager.popBackStack(tag, flag)
    }

    fun isFragmentOnTopOfStack(
        fragmentManager: FragmentManager,
        tag: String,
        @IdRes containerId: Int
    ): Boolean {
        val fragment = fragmentManager.findFragmentById(containerId)
        return (fragment != null && fragment.tag == tag)
    }
}