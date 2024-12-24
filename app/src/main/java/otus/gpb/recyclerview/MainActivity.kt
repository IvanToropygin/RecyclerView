package otus.gpb.recyclerview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val testData = DataGenerator.generateChatItems(15)
    private val chatAdapter: ChatAdapter by lazy { ChatAdapter(testData) }
    private lateinit var binding: ActivityMainBinding

    private var isLoading = false
    private var totalItemCount = 0
    private var lastVisibleItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerViewChatList.adapter = chatAdapter
        binding.recyclerViewChatList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.recyclerViewChatList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = chatAdapter.itemCount
                lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (totalItemCount <= (lastVisibleItem + 1) && !isLoading) {
                    loadMoreData()
                }
            }
        })

        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.absoluteAdapterPosition
                    chatAdapter.removeItem(position)
                }
            })

        itemTouchHelper.attachToRecyclerView(binding.recyclerViewChatList)
    }

    private fun loadMoreData() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            val newItems = DataGenerator.generateChatItems(5)
            chatAdapter.addItems(newItems)
            isLoading = false
            binding.progressBar.visibility = View.GONE
        }, 2000)
    }
}