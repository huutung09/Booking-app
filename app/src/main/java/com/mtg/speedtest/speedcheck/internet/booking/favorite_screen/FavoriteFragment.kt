package com.mtg.speedtest.speedcheck.internet.booking.favorite_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mtg.speedtest.speedcheck.internet.booking.SingletonClass
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentFavoriteBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.Province

class FavoriteFragment : Fragment() {
    companion object {
        private val TAG = FavoriteFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val listFavorite = mutableListOf<HotTrend>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        getListFavorite()
    }

    private fun initViews() {
        favoriteAdapter = FavoriteAdapter(requireContext(),
            listFavorite
        ) { hotTrend, _ ->
            val intent = Intent(requireContext(), DetailHotTrend::class.java)
            intent.putExtra("key_detail_hotTrend", hotTrend)
            startActivity(intent)
        }
        val layoutManagerProvince: RecyclerView.LayoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        binding.revFavorite.layoutManager = layoutManagerProvince
        binding.revFavorite.adapter = favoriteAdapter
    }

    private fun getListFavorite () {
        db.collection("users")
            .document(auth.currentUser!!.uid)
            .collection("favorite")
            .get()
            .addOnSuccessListener {
                listFavorite.clear()
                for (document in it) {
                    val hotTrend = document.toObject(HotTrend::class.java)
                    listFavorite.add(hotTrend)
                }
                favoriteAdapter.notifyDataSetChanged()
            }
    }
}