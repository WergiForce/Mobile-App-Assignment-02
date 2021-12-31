package org.wit.geosite.views.geositelist
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import org.wit.geosite.R
import org.wit.geosite.adapters.GeositeAdapter
import org.wit.geosite.adapters.GeositeListener
import org.wit.geosite.databinding.ActivityGeositeListBinding
import org.wit.geosite.main.MainApp
import org.wit.geosite.models.GeositeModel
import timber.log.Timber.i

class GeositeListView : AppCompatActivity(), GeositeListener {

    lateinit var app: MainApp
    lateinit var binding: ActivityGeositeListBinding
    lateinit var presenter: GeositeListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityGeositeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = GeositeListPresenter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        updateRecyclerView()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {

        //update the view
        super.onResume()
        updateRecyclerView()
        binding.recyclerView.adapter?.notifyDataSetChanged()
        i("recyclerView onResume")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddGeosite() }
            R.id.item_map -> { presenter.doShowGeositesMap() }
            R.id.item_logout -> { presenter.doLogout() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGeositeClick(geosite: GeositeModel) {
        presenter.doEditGeosite(geosite)

    }

    private fun updateRecyclerView(){
        GlobalScope.launch(Dispatchers.Main){
            binding.recyclerView.adapter =
                GeositeAdapter(presenter.getGeosites(), this@GeositeListView)
        }
    }

}