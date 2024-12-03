package com.mtg.speedtest.speedcheck.internet.booking.bookmark_screen

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
import com.mtg.speedtest.speedcheck.internet.booking.databinding.FragmentBookmarkBinding
import com.mtg.speedtest.speedcheck.internet.booking.detail_hottrend.DetailHotTrend
import com.mtg.speedtest.speedcheck.internet.booking.model.HotTrend

class BookmarkFragment : Fragment() {
    companion object {
        private val TAG = BookmarkFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val listBookmark = mutableListOf<HotTrend>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        getListBookmark()
    }

    private fun initViews() {
        bookmarkAdapter = BookmarkAdapter(requireContext(), listBookmark) { hotTrend, _ ->
            val intent = Intent(requireContext(), DetailHotTrend::class.java)
            intent.putExtra("key_detail_hotTrend", hotTrend)
            startActivity(intent)
        }
        val layoutManagerProvince: RecyclerView.LayoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        binding.revBookmark.layoutManager = layoutManagerProvince
        binding.revBookmark.adapter = bookmarkAdapter
    }

    private fun getListBookmark () {
        db.collection("users")
            .document(auth.currentUser!!.uid)
            .collection("bookmark")
            .get()
            .addOnSuccessListener {
                listBookmark.clear()
                for (document in it) {
                    val hotTrend = document.toObject(HotTrend::class.java)
                    listBookmark.add(hotTrend)
                }
                bookmarkAdapter.notifyDataSetChanged()
            }
    }
}