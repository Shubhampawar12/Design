
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.desinglogin.DataFragment

class DataAdaper(fm: FragmentManager, private val data: List<List<Data>>) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        val fragment = DataFragment()
        val bundle = Bundle()
        bundle.putSerializable("dataList", ArrayList(data[position]))
        fragment.arguments = bundle
        return fragment
    }

}
