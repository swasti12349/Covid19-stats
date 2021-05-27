package com.sro.covid_19stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sro.newsapp.coronaAdapter
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var confirmed: ArrayList<String>
    private lateinit var active: ArrayList<String>
    private lateinit var recovered: ArrayList<String>
    private lateinit var deceased: ArrayList<String>
    private lateinit var state: Array<String>
    val url = "https://api.covid19india.org/v4/min/data.min.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerViewID)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        confirmed = ArrayList<String>()
        active = ArrayList()
        recovered = ArrayList()
        deceased = ArrayList()


        state = arrayOf(
            "Andaman and Nicobar",
            "Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chandigarh",
            "Chhattisgarh",
            "Dadra And Nagar Haveli and Daman And Diu",
            "Delhi",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Ladakh",
            "Lakshadweep",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Puducherry",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttar Pradesh",
            "Uttarakhand",
            "West Bengal",
            "India"
        )

        refresh()


    }
    fun refresh(){
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val st = arrayOf(
                    "AN", "AP", "AR", "AS", "BR", "CH", "CT", "DN",
                    "DL", "GA", "GJ", "HP", "HR", "JH", "JK", "KA",
                    "KL", "LA", "LD", "MH", "ML", "MN", "MP", "MZ",
                    "NL", "OR", "PB", "PY", "RJ", "SK", "TG", "TN",
                    "TR", "UP", "UT", "WB", "TT"
                )
                for (i in 0..36) {
                    val obj: JSONObject = response.getJSONObject(st[i]).getJSONObject("total")
                    confirmed.add(i, obj.getString("confirmed"))
                    Log.d("et", confirmed.toString())


                    recovered.add(obj.getString("recovered"))
                    deceased.add(obj.getString("deceased"))
                    val con: Int = obj.getString("confirmed").toInt()
                    val rec: Int = obj.getString("recovered").toInt()
                    val dec: Int = obj.getString("deceased").toInt()
                    active.add((con - (rec + dec)).toString())
                }
                val cadapter = coronaAdapter(state, confirmed,active, recovered, deceased)
                recyclerView.adapter = cadapter
            },
            { error ->
                // Handle error
                Log.d("errorAPI", error.toString())
            })

        queue.add(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menuitem, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}