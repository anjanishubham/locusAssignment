package com.example.locusassignment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locusassignment.UI.PreviewImage
import com.example.locusassignment.adapter.CustomListViewAdapter
import com.example.locusassignment.adapter.VerticalSpaceItemDecoration
import com.example.locusassignment.databinding.ActivityMainBinding
import com.example.locusassignment.datamodule.ResponseData
import com.example.locusassignment.listeners.ClickListener
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() , ClickListener{
    lateinit var responseData: ResponseData
    lateinit var adapter: CustomListViewAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var imageUri: Uri

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){
        adapter.notifyDataSetChanged()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readDataFromJsonFile()
    }


    private fun readDataFromJsonFile(){
        try {
            val data: InputStream = assets.open("responseData.json")
            val size: Int = data.available()
            val buffer = ByteArray(size)
            data.read(buffer)
            data.close()
            val json = String(buffer)
           responseData= Gson().fromJson(json,ResponseData::class.java)
            initAdapter()
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.d("output", "errror"+ex.toString())
        }

    }

  private  fun initAdapter() {
        adapter = CustomListViewAdapter(responseData,this)
        binding.rv.visibility = View.VISIBLE
        val layManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = layManager
        binding.rv.addItemDecoration(VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE))
        binding.rv.adapter = adapter
    }

    override fun openCamera(position : Int) {
        if(responseData[position].dataMap.options.isNullOrEmpty().not()){
            PreviewImage.newInstance((responseData[position].dataMap.options.first())).show(supportFragmentManager, PreviewImage.TAG)
        }else {
            imageUri = createImageUri(position)
            responseData[position].dataMap.options = arrayListOf(imageUri.toString())
            contract.launch(imageUri)
        }
    }

    override fun removeImage(position: Int) {
        deleteImageUri(position)
    }

    private fun deleteImageUri(position: Int){
        val dir = filesDir
        val file = File(dir, FILE_NAME+position)
        file.delete()
        responseData[position].dataMap.options.clear()
        adapter.notifyDataSetChanged()
    }

    private fun createImageUri(position: Int): Uri{
        val image = File(applicationContext.filesDir, FILE_NAME+position)
        return FileProvider.getUriForFile(applicationContext,
            "com.example.locusassignment.fileProvider",
                    image)
    }
    companion object{
        const val VERTICAL_ITEM_SPACE = 24
        const val FILE_NAME="camera_photo.png"
    }
}