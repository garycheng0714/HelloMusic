package com.kkbox.hellomusic

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.kkbox.hellomusic.utils.Sort
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SortUnitTest {
    val json = """
        [
            {"name":"1","album":{"release_date":"2019-03-15","artist":{"name":"zzzz"}}},
            {"name":"3","album":{"release_date":"2019-03-01","artist":{"name":"kkkk"}}},
            {"name":"4","album":{"release_date":"2019-03-08","artist":{"name":"cccc"}}},
            {"name":"5","album":{"release_date":"2019-03-03","artist":{"name":"bbbb"}}},
            {"name":"2","album":{"release_date":"2019-03-27","artist":{"name":"aaaa"}}}
        ]
    """.trimIndent()
    val gson = GsonBuilder().setPrettyPrinting().create()

    private val sort = Sort()

    @Test
    fun testSortByArtist() {
        val testJson:ArrayList<JsonElement> = gson.fromJson(json, object : TypeToken<ArrayList<JsonElement>>() {}.type)
        val expectedData = """
        [
            {"name":"2","album":{"release_date":"2019-03-27","artist":{"name":"aaaa"}}},
            {"name":"5","album":{"release_date":"2019-03-03","artist":{"name":"bbbb"}}},
            {"name":"4","album":{"release_date":"2019-03-08","artist":{"name":"cccc"}}},
            {"name":"3","album":{"release_date":"2019-03-01","artist":{"name":"kkkk"}}},
            {"name":"1","album":{"release_date":"2019-03-15","artist":{"name":"zzzz"}}}
        ]
    """.trimIndent()

        val expectedJson:ArrayList<JsonElement> = gson.fromJson(expectedData, object : TypeToken<ArrayList<JsonElement>>() {}.type)

        sort.sortByArtist(testJson).toString()

        val actual = testJson.toString()
        assertEquals(expectedJson.toString(), actual)
    }

    @Test
    fun testSortBySongName() {
        val testJson:ArrayList<JsonElement> = gson.fromJson(json, object : TypeToken<ArrayList<JsonElement>>() {}.type)
        val expectedData = """
        [
            {"name":"1","album":{"release_date":"2019-03-15","artist":{"name":"zzzz"}}},
            {"name":"2","album":{"release_date":"2019-03-27","artist":{"name":"aaaa"}}},
            {"name":"3","album":{"release_date":"2019-03-01","artist":{"name":"kkkk"}}},
            {"name":"4","album":{"release_date":"2019-03-08","artist":{"name":"cccc"}}},
            {"name":"5","album":{"release_date":"2019-03-03","artist":{"name":"bbbb"}}}
        ]
    """.trimIndent()

        val expectedJson:ArrayList<JsonElement> = gson.fromJson(expectedData, object : TypeToken<ArrayList<JsonElement>>() {}.type)

        sort.sortBySongName(testJson).toString()

        val actual = testJson.toString()
        assertEquals(expectedJson.toString(), actual)
    }

    @Test
    fun testSortByDate() {
        val testJson:ArrayList<JsonElement> = gson.fromJson(json, object : TypeToken<ArrayList<JsonElement>>() {}.type)
        val expectedData = """
        [
            {"name":"2","album":{"release_date":"2019-03-27","artist":{"name":"aaaa"}}},
            {"name":"1","album":{"release_date":"2019-03-15","artist":{"name":"zzzz"}}},
            {"name":"4","album":{"release_date":"2019-03-08","artist":{"name":"cccc"}}},
            {"name":"5","album":{"release_date":"2019-03-03","artist":{"name":"bbbb"}}},
            {"name":"3","album":{"release_date":"2019-03-01","artist":{"name":"kkkk"}}}
        ]
    """.trimIndent()

        val expectedJson:ArrayList<JsonElement> = gson.fromJson(expectedData, object : TypeToken<ArrayList<JsonElement>>() {}.type)

        sort.sortByDate(testJson).toString()

        val actual = testJson.toString()
        assertEquals(expectedJson.toString(), actual)
    }
}
