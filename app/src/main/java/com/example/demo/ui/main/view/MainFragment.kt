package com.example.demo.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.demo.R
import com.example.demo.base.BaseFragment
import com.example.demo.databinding.FragmentMainBinding
import com.example.demo.ui.get_user.view.GetAllUserFragment
import com.example.demo.ui.main.view.pager_adapter.MainViewPagerAdapter
import com.example.demo.ui.mandobk_shop.view.MandoBkShopFragment
import com.example.demo.ui.get_user.view.MeFragment
import com.example.demo.ui.my_order.view.MyOrderFragment
import com.example.demo.ui.nearby_shop.view.NearByShopFragment
import com.example.demo.utils.TabEnums
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : BaseFragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MainViewPagerAdapter
    private var tabPosition: Int? = null
    var getAllUserFragment: GetAllUserFragment? = null
    var mandoBkShopFragment: MandoBkShopFragment? = null
    var nearByShopFragment: NearByShopFragment? = null
    var myOrderFragment: MyOrderFragment? = null
    private var tempString: String? = null
    private var flagMessage = false


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * This method is used to initialize views for this screen
     */
    private fun initView() {
        setTabLayoutMethod()
    }

    /**
     * This method is used to Set Tab Layout For this Screen
     */
    private fun setTabLayoutMethod() {
        try {

            getAllUserFragment = GetAllUserFragment()
            mandoBkShopFragment = MandoBkShopFragment()
            nearByShopFragment = NearByShopFragment()
            myOrderFragment = MyOrderFragment()


            adapter =
                MainViewPagerAdapter(this)

            adapter.addFragment(getAllUserFragment!!)
            adapter.addFragment(mandoBkShopFragment!!)
            adapter.addFragment(nearByShopFragment!!)
            adapter.addFragment(myOrderFragment!!)



            binding.viewPagerVP.adapter = adapter
            binding.viewPagerVP.offscreenPageLimit = 3
            TabLayoutMediator(binding.tabLayoutTL, binding.viewPagerVP
            ) { tab, position -> tab.text = "Tab " + (position + 1) }.attach()

            tabUndressed()
            setupTabs()

        } catch (e: Exception) {
            print(e.printStackTrace())
        }
    }

    private fun tabUndressed() {
        binding.tabLayoutTL.getTabAt(TabEnums.MAN_DO_BK.posVal)!!.setIcon(R.mipmap.ic_launcher).text =
            TabEnums.MAN_DO_BK.staringVal
        binding.tabLayoutTL.getTabAt(TabEnums.NEAR_BY.posVal)!!.setIcon(R.mipmap.ic_launcher).text =
            TabEnums.NEAR_BY.staringVal
        binding.tabLayoutTL.getTabAt(TabEnums.MY_ORDER.posVal)!!.setIcon(R.mipmap.ic_launcher).text =
            TabEnums.MY_ORDER.staringVal
        binding.tabLayoutTL.getTabAt(TabEnums.ME.posVal)!!.setIcon(R.mipmap.ic_launcher)
            .text = TabEnums.ME.staringVal
    }

    private fun setupTabs() {
        for (i in 0 until binding.tabLayoutTL.tabCount) {
            val tab = binding.tabLayoutTL.getTabAt(i)
            @Suppress("INACCESSIBLE_TYPE")
            (tab?.view as? View)?.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(p0: View?, event: MotionEvent): Boolean {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        return true
                    } else if (event.action == MotionEvent.ACTION_UP) {
                        tabPosition = i
                        binding.viewPagerVP.currentItem = tabPosition!!
                    }
                    return false
                }
            })
        }
    }

}