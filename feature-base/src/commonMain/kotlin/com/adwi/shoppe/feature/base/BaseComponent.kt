package com.adwi.shoppe.feature.base

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

interface BaseComponent {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Data1(val name: String) : Child()
        data class Data2(val name: String) : Child()
    }

    fun onData()
}