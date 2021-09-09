package com.example.cinemalib.framework.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.databinding.MapFragmentBinding
import com.example.cinemalib.model.AppState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class MapsFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var map: GoogleMap
    private var menu: Menu? =null
    private var markers: ArrayList<Marker> = ArrayList()
    private val viewModel: MapsViewModel by viewModel()
    private var _binding: MapFragmentBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            map.isMyLocationEnabled = true
        }
        val initialPlace = LatLng(59.9311, 30.3609)
        val marker = googleMap.addMarker(
            MarkerOptions().position(initialPlace).title("Start")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MapFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        initSearchByAddress()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.maps_menu, menu)
        this.menu = menu
        menu.findItem(R.id.menu_map).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_map_mode_normal -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
                return true
            }
            R.id.menu_map_mode_satellite -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                return true
            }
            R.id.menu_map_mode_terrain -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
                return true
            }
            R.id.menu_map_traffic -> {
                map.isTrafficEnabled = !map.isTrafficEnabled
                return true
            }
        }

        return false
    }

    private fun initSearchByAddress() = with(binding) {
        buttonSearch.setOnClickListener {
            val geoCoder = Geocoder(it.context)
            val searchText = mapSearchAddress.text.toString()
            launch(Dispatchers.IO) {
                try {
                    val addresses = geoCoder.getFromLocationName(searchText, 1)
                    if (addresses.isNotEmpty()){
                        goToAddress(addresses, it, searchText)
                    }
                } catch (e: IOException){
                    e.printStackTrace()
                }
            }

        }
        val id = arguments?.getInt(BUNDLE_EXTRA, 0)
        id?.let {
            viewModel.loadData(it)
            viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
                when (appState){
                    is AppState.SuccessPlaceOfBirth -> {
                        mapSearchAddress.setText(appState.placeOfBirth)
                        buttonSearch.performClick()
                    }
                    is AppState.Error -> {
                        Toast.makeText(context, resources.getString(R.string.error), Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun goToAddress(addresses: List<Address>, view: View, searchText: String) {
        val location = LatLng(addresses[0].latitude, addresses[0].longitude)
        launch {
            setMarker(location, searchText)
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    location,
                    15f
                )
            )
        }
    }

    private fun setMarker(location: LatLng, searchText: String) {
        map.addMarker(
            MarkerOptions()
                .position(location)
                .title(searchText)
        )?.let { markers.add(it) }

    }


    companion object {
        const val BUNDLE_EXTRA = "place data"

        fun newInstance(bundle: Bundle): MapsFragment {
            val fragment = MapsFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance() = MapsFragment()
    }
}