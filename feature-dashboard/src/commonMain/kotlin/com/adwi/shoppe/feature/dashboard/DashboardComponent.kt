package com.adwi.shoppe.feature.dashboard

import com.adwi.shoppe.feature.details.ShopPreviewComponent
import com.adwi.shoppe.feature.shops.ShopsComponent
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

interface DashboardComponent {

    val shops: ShopsComponent

    val routerState: Value<RouterState<*, Child>>

    fun onOrderClick(id: String)
    fun signOut()

    sealed class Child() {
        data class Dashboard(val component: DashboardComponent) : Child()
        data class ShopDetails(val component: ShopPreviewComponent) : Child()
        data class OrderDetails(val component: ShopPreviewComponent) : Child()
    }
}