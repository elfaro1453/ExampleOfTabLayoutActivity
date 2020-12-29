package kuy.belajar.exampleoftablayoutactivity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kuy.belajar.exampleoftablayoutactivity.adapters.ItemFollowRecyclerViewAdapter
import kuy.belajar.exampleoftablayoutactivity.R
import kuy.belajar.exampleoftablayoutactivity.model.GithubUser
import org.json.JSONArray

/**
 * A fragment representing a list of Items.
 */
class FollowingFragment : Fragment() {

    companion object {

        const val USERNAME_FOLLOWING = "username"

        // buat fungsi newInstance dengan parameter username:String yang diisi di SectionsPagerAdapter
        @JvmStatic
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME_FOLLOWING, username)
                }
            }
    }

    // buat variabel global recylerview adapter
    private val recylerViewAdapter = ItemFollowRecyclerViewAdapter()

    // buat variabel global username dengan nilai awal "null"
    private var username = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // jika arguments tidak null, ambil data username
        arguments?.let {
            username = it.getString(USERNAME_FOLLOWING, username)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_following, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = recylerViewAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setelah semua view dibuat, jalankan fungsi showfollowing
        showFollowing(username)
    }

    private fun showFollowing(username: String) {
        val listItems = ArrayList<GithubUser>()

        val asyncClient = AsyncHttpClient()
        // disini saya tidak menambahkan token, seharusnya tambahkan token juga ya
        asyncClient.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/$username/following"

        asyncClient.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    // ubah tipe data responsebody menjadi string
                    val result = responseBody?.let { String(it) }
                    // jika result tidak null
                    result?.let {
                        // jadikan result menjadi array response
                        val responseArray = JSONArray(result)

                        // cek berapa banyak data
                        Log.d("FollowingCount", responseArray.length().toString())

                        // setiap anggota array kita lakukan pengulangan
                        for (i in 0 until responseArray.length()) {
                            val jsonObject = responseArray.getJSONObject(i)
                            val mModel = GithubUser()

                            /* karena saya hanya membutuhkan username untuk ditampilkan di recyclerview
                            * maka saya hanya mengambil data login saja
                            * semisal anda ingin menambahkan avatar di recylerview, maka ambil data avatar
                            * dengan cata
                            * mModel.avatar = jsonObject.getString("avatar")
                             */
                            mModel.login = jsonObject.getString("login")
                            listItems.add(mModel)
                        }
                        // tambahkan data ke recylerviewAdapter
                        recylerViewAdapter.setData(listItems)
                    }
                } catch (e: Exception) {
                    Log.e("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                if (error != null) {
                    Log.e("onFailure", error.message.toString())
                }
            }
        })
    }

}