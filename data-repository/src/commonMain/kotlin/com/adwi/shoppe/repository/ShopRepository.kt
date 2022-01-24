package com.adwi.shoppe.repository

import com.adwi.kotlin.data.local.Shop
import com.adwi.shoppe.CreateShopMutation
import com.adwi.shoppe.DeleteShopMutation
import com.adwi.shoppe.GetProfileQuery
import com.adwi.shoppe.GetShopByIdQuery
import com.adwi.shoppe.ShopsPagedByUserIdQuery
import com.adwi.shoppe.UpdateShopMutation
import com.adwi.shoppe.data.api.ApolloProvider
import com.adwi.shoppe.data.local.mapper.ShopDetail
import com.adwi.shoppe.data.local.mapper.Shops
import com.adwi.shoppe.data.local.mapper.toShop
import com.adwi.shoppe.data.local.mapper.toShopDetail
import com.adwi.shoppe.data.local.mapper.toShops
import com.adwi.shoppe.type.ShopInput
import com.apollographql.apollo3.api.Optional

class ShopRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun getProfileShops(): List<Shop> {
        val response = apolloClient.query(GetProfileQuery()).execute()
        return response.data?.getProfile?.shops?.map { it.toShop() } ?: emptyList()
    }

    suspend fun getShopsPaged(page: Optional<Int>, size: Optional<Int>): Shops? {
        val response = apolloClient.query(ShopsPagedByUserIdQuery(page, size)).execute()
        return response.data?.shopsPagedByUserId?.toShops()
    }

    suspend fun getShop(shopId: String): ShopDetail? {
        val response = apolloClient.query(GetShopByIdQuery(shopId)).execute()
        return response.data?.getShopById?.toShopDetail()
    }

    suspend fun createShop(shopInput: ShopInput): Shop? {
        val response = apolloClient.mutation(CreateShopMutation(shopInput)).execute()
        return response.data?.createShop?.toShop()
    }

    suspend fun updateShop(shopId: String, shopInput: ShopInput): Shop? {
        val response = apolloClient.mutation(UpdateShopMutation(shopId, shopInput)).execute()
        return response.data?.updateShop?.toShop()
    }

    suspend fun deleteShop(shopId: String): Boolean? {
        val response = apolloClient.mutation(DeleteShopMutation(shopId)).execute()
        return response.data?.deleteShop
    }

    fun saveFavorite(shop: Shop) {
        database.saveShop(shop)
    }

    fun removeFavorite(shopId: String) {
        database.deleteShop(shopId)
    }

    fun updateFavorite(shop: Shop) {
        database.updateShop(shop)
    }

    fun getFavoriteShop(shopId: String): Shop? {
        return database.getShopById(shopId)
    }

    fun getFavoriteShops(): List<Shop> {
        return database.getShops()
    }
}