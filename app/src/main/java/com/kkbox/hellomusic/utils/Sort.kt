package com.kkbox.hellomusic.utils

import android.os.Build
import android.support.annotation.RequiresApi
import com.google.gson.JsonElement
import org.json.JSONException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Sort {

     fun sortBySongName(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                // TODO Auto-generated method stub

                return try {
                    lhs.asJsonObject.get("name").toString().compareTo(rhs.asJsonObject.get("name").toString())
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }

     fun sortByArtist(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                // TODO Auto-generated method stub
                return try {
                    lhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString().compareTo(rhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString())
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }

     fun sortByDate(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            @RequiresApi(Build.VERSION_CODES.O)
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                val lhsSongName = lhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString()
                val rhsSongName = rhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString()
                val songNameSort  = lhsSongName > rhsSongName
                val lhsDate = LocalDate.parse(lhs.asJsonObject.get("album").asJsonObject.get("release_date").toString().replace("\"",""), DateTimeFormatter.ISO_DATE)
                val rhsDate = LocalDate.parse(rhs.asJsonObject.get("album").asJsonObject.get("release_date").toString().replace("\"",""), DateTimeFormatter.ISO_DATE)
                var flag: Int
                if(lhsDate.isAfter(rhsDate)){
                    flag = -1
                }else if(lhsDate.isEqual(rhsDate)){
                    flag = if(songNameSort)
                        -1
                    else
                        1
                }else{
                    flag = 1
                }
                // TODO Auto-generated method stub
                return try {
                    flag
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }
}