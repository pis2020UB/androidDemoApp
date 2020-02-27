package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rl)

        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        setSupportActionBar(findViewById(R.id.toolbar))


        val spinner: Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.country_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }



        // --------------------------------------------
        // LAB 3.1. Request to Google
        /*
        val textView: TextView = findViewById(R.id.contentTV)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://google.com"

        val runButton: Button = findViewById(R.id.runButton)
        runButton.setOnClickListener {
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                Response.ErrorListener { volleyError ->
                    textView.text = "That didn't work! Response ${volleyError}" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

         */









        // --------------------------------------------
        // LAB 3.2. Request to OpenAQ
        /*
        val textView: TextView = findViewById(R.id.contentTV)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openaq.org/v1/countries"

        val runButton: Button = findViewById(R.id.runButton)
        runButton.setOnClickListener {
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                Response.ErrorListener { volleyError ->
                    textView.text = "That didn't work! Response ${volleyError}" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }
         */











        // --------------------------------------------
        // LAB 3.3. Shoe measurements by country
        /*
        val textView: TextView = findViewById(R.id.contentTV)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openaq.org/v1/measurements?country="

        val runButton: Button = findViewById(R.id.runButton)
        runButton.setOnClickListener {
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url + spinner.getSelectedItem().toString(),
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                Response.ErrorListener { volleyError ->
                    textView.text = "That didn't work! Response ${volleyError}" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

         */







        // --------------------------------------------
        // LAB 3.4. Format text of response
        val textView: TextView = findViewById(R.id.contentTV)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openaq.org/v1/measurements?parameter=co&country="

        val runButton: Button = findViewById(R.id.runButton)
        runButton.setOnClickListener {
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url + spinner.getSelectedItem().toString(),
                Response.Listener<String> { response ->
                    // Parse response to JSON
                    val json = JSONObject(response)
                    val results = json.getJSONArray("results")
                    // Create auxiliary variables to concatenate text and save JSON
                    var text = "Response is:\n\n"
                    var auxJson: JSONObject? = null
                    for (i in 0 until (results.length()-1)) {
                        auxJson = results.getJSONObject(i)
                        text += "${auxJson.get("city")} "
                        text += "${auxJson.get("value")} ${auxJson.get("unit")}\n"
                    }
                    textView.text = text
                },
                Response.ErrorListener { volleyError ->
                    textView.text = "That didn't work! Response ${volleyError}" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

}
