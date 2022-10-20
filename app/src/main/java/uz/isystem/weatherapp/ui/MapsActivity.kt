package uz.isystem.weatherapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.isystem.weatherapp.R
import uz.isystem.weatherapp.R.layout.activity_maps
import uz.isystem.weatherapp.core.cache.MemoryHelper
import uz.isystem.weatherapp.core.cache.UserData
import java.io.IOException

internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var searchView: SearchView
    private lateinit var addLocation: FloatingActionButton
    private lateinit var myLocation: FloatingActionButton
    private lateinit var client: FusedLocationProviderClient

    private var lat: Double? = null
    private var lon: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).also {
            mapFragment = it
        }
        mapFragment.getMapAsync(this)
        searchView = findViewById(R.id.sv_location)
        addLocation = findViewById(R.id.add_location)
        myLocation = findViewById(R.id.my_location)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val location = searchView.query.toString()
                var addressList: List<Address>? = null
                val e: IOException? = null

                if (location != null || location != "") {
                    val geocoder = Geocoder(this@MapsActivity)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(this@MapsActivity, "try again", Toast.LENGTH_SHORT).show()
                    } finally {

                    }
                    val address: Address? = addressList?.firstOrNull()

                    address?.let {
                        val latLng = LatLng(address.latitude, address.longitude)
                        lat = address.latitude
                        lon = address.longitude

                        mMap.addMarker(MarkerOptions().position(latLng).title(location))
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14F))
                    }


                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        mapFragment.getMapAsync(this@MapsActivity)

        addLocation.setOnClickListener {
            var intent = Intent(this, WeatherActivity::class.java)

            MemoryHelper.helper!!.clearData()
            MemoryHelper.helper!!.saveData(
                UserData(
                    latitude = lat!!.toFloat(),
                    longitude = lon!!.toFloat()
                )
            )

            startActivity(intent)
            finish()
        }

        myLocation.setOnClickListener {

            client = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this@MapsActivity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("fff", "laslas")
                getMyCurrentLocation()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 44
                )
            }

        }
    }

    @SuppressLint("MissingPermission")
    private fun getMyCurrentLocation() {
        val task: Task<Location> = client.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                mapFragment.getMapAsync { googleMap ->
                    var latLng = LatLng(location.latitude, location.longitude)
                    lat = location.latitude
                    lon = location.longitude

                    var options = MarkerOptions().position(latLng).title("I am There")

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F))
                    googleMap.addMarker(options)
                }
            } else {
                Toast.makeText(
                    this, "\uD83D\uDCCD  Your location has been disabled\n" +
                            "Please enable location", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.also { mMap = it }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 44) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyCurrentLocation()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
        finish()
    }
}