package com.example.recycleview.place
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val points: Float,
    val date: String,
    val participants: Float,
    val rating: Float
) : ClusterItem {
    override fun getPosition(): LatLng =
        latLng

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        address
}

