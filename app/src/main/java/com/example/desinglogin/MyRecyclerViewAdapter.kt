    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.desinglogin.R

    class MyRecyclerViewAdapter(private var dataList: List<Data>) :
        RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.comments_layout, parent, false)
            return ViewHolder(view)
        }
        fun updateData(newDataList: List<Data>) {
            dataList = newDataList
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = dataList[position]
            holder.bind(data)
        }
        fun removeItem(position: Int) {
            dataList = dataList.toMutableList().apply { removeAt(position) }
            notifyItemRemoved(position)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val postidTextView: TextView = itemView.findViewById(R.id.postid)
            private val useridTextView: TextView = itemView.findViewById(R.id.id)
            private val nameTextView: TextView = itemView.findViewById(R.id.name)
            private val emailTextView: TextView = itemView.findViewById(R.id.email)
            private val bodyTextView: TextView = itemView.findViewById(R.id.body)

            fun bind(data: Data) {
                postidTextView.text = data.postId.toString()
                useridTextView.text = data.id.toString()
                nameTextView.text = data.name
                emailTextView.text = data.email
                bodyTextView.text = data.body
            }
        }
    }
