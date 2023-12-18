package com.example.recycleview.Fragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleview.R
import com.example.recycleview.databinding.FragmentHomeBinding
import com.example.recycleview.databinding.FragmentMapBinding
import com.example.recycleview.place.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.recycleview.place.PlacesReader
import com.example.recycleview.pojo.Event


class mapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

    private val places: List<Place> by lazy {
        PlacesReader(requireContext()).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng(36.898439, 10.180599))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)


            //val lat = sp?.let { getDouble(it, "lat", 0.0) }
           // val lag = sp?.let { getDouble(it, "lag", 0.0) }


              mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(36.866606, 10.349390))
                   .title("Cleaning Sidi Bou Said Beach event")
                   .snippet("reward : 1800 points / 15 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(36.887269, 10.330595))
                   .title("Cleaning Gammarth Beach event")
                   .snippet("reward : 2500 points / 11 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(36.996466, 9.032202))
                   .title("Cleaning Barrage Sidi El Barrak forest")
                   .snippet("reward : 2000 points / 36 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(34.724404, 11.191038))
                   .title("Cleaning Kerkanah Beach event")
                   .snippet("reward : 1800 points / 13 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(33.159362, 9.170983))
                   .title("Cleaning Jebil Park event")
                   .snippet("reward : 1300 points / 8 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(37.344092, 9.743537))
                   .title("Cleaning Cap Angela Forest")
                   .snippet("reward : 1700 points / 4 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(37.334543, 9.691692))
                   .title("Cleaning Cap Hmem Beach event")
                   .snippet("reward : 1850 points / 16 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(37.314869, 9.739871))
                   .title("Cleaning Douar Ghiran Forest")
                   .snippet("reward : 1450 points / 11 participant")

           )
           mMap.addMarker(
               MarkerOptions()
                   .position(LatLng(37.322572, 9.799224))
                   .title("Cleaning Ain Damous Beach event")
                   .snippet("reward : 1700 points / 17 participant"),

               )


        }}




}