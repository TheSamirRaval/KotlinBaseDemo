package com.example.demo.base.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.demo.R
import kotlin.reflect.KClass

interface FragmentNavigation {
    fun AppCompatActivity.navigateTo(
        fragment: Fragment, transition: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            if (transition) {
                setCustomAnimations(
                    R.anim.enter_slide_right,
                    R.anim.exit_slide_right,
                    R.anim.pop_enter_slide_right,
                    R.anim.pop_exit_slide_right
                )
            }
            addToBackStack(fragment.backStackTag)
            replace(R.id.container_main, fragment, fragment.fragmentTag)
            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.addNew(
        id:Int,
        fragment: Fragment,
        transition: Boolean = false,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (transition) {
                setCustomAnimations(
                    R.anim.enter_slide_right,
                    R.anim.exit_slide_right,
                    R.anim.pop_enter_slide_right,
                    R.anim.pop_exit_slide_right
                )
            }
            addToBackStack(fragment.javaClass.simpleName)
            add(id, fragment, fragment.javaClass.simpleName)
            if (targetFragment != null && requestCode != null) {
                fragment.setTargetFragment(targetFragment, requestCode)
            }
            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.addNewHome(
        fragment: Fragment,
        transition: Boolean = false,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (transition) {
                setCustomAnimations(
                    R.anim.enter_slide_right,
                    R.anim.exit_slide_right,
                    R.anim.pop_enter_slide_right,
                    R.anim.pop_exit_slide_right
                )
            }
            addToBackStack(fragment.backStackTag)
            //add(R.id.container_home, fragment, fragment.fragmentTag)
            if (targetFragment != null && requestCode != null) {
                fragment.setTargetFragment(targetFragment, requestCode)
            }
            commitSafely(supportFragmentManager)
        }
    }
    fun AppCompatActivity.slideUp(
        fragment: Fragment,
        transition: Boolean = false,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (transition) {
                setCustomAnimations(
                    R.anim.enter_slide_down,
                    0,
                    0,
                    R.anim.exit_slide_down
                )
            }
            addToBackStack(fragment.backStackTag)
            add(R.id.container_main, fragment, fragment.fragmentTag)
            if (targetFragment != null && requestCode != null) {
                fragment.setTargetFragment(targetFragment, requestCode)
            }
            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.slideTo(fragment: Fragment) {
        navigateTo(fragment, true)
    }

    fun AppCompatActivity.switchFromTo(fromFragment: Fragment?, toFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            fromFragment?.let { hide(it) }

            if (toFragment.isAdded) {
                show(toFragment)
            } else {
                add(R.id.container_main, toFragment, toFragment.fragmentTag)
            }

            // Let fragments know they have been shown/hidden
            fromFragment?.userVisibleHint = false
            this.runOnCommit { toFragment.userVisibleHint = true }

            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.setFragmentAsRoot(id:Int,fragment: Fragment) {
        supportFragmentManager.findFragmentById(id)

        val currentFragmentClass = supportFragmentManager.getCurrentFragment()?.let { it::class }
        val fragmentClass = fragment::class
        if (currentFragmentClass == fragmentClass) {
            return
        }

        supportFragmentManager.clearBackStack()
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment, fragment.fragmentTag)
            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.setFragmentAsRootHome(fragment: Fragment) {
        //supportFragmentManager.findFragmentById(R.id.container_home)

        val currentFragmentClass = supportFragmentManager.getCurrentFragment()?.let { it::class }
        val fragmentClass = fragment::class
        if (currentFragmentClass == fragmentClass) {
            return
        }

        supportFragmentManager.clearBackStack()
        supportFragmentManager.beginTransaction().apply {
           // replace(R.id.container_home, fragment, fragment.fragmentTag)
            commitSafely(supportFragmentManager)
        }
    }

    fun AppCompatActivity.popBackStack() = supportFragmentManager.popBackStackIfSafe()

    fun AppCompatActivity.getFragment(fragmentClass: KClass<out Fragment>): Fragment? =
        supportFragmentManager.findFragmentByTag(fragmentClass.fragmentTag)

    fun AppCompatActivity.getCurrentFragment(): Fragment? =
        supportFragmentManager.getCurrentFragment()

    private fun FragmentManager.clearBackStack() {
        if (!isStateSaved) {
            while (popBackStackImmediate()) {
                // nothing to do
            }
        }
    }
}

private val Fragment.fragmentTag: String
    get() = this.javaClass.name

private val Fragment.backStackTag: String
    get() = this.javaClass.name

val KClass<out Fragment>.fragmentTag: String
    get() = this.java.name

private fun FragmentManager.getCurrentFragment(): Fragment? {
    val backStackCount = backStackEntryCount

    if (backStackCount > 0) {
        val fragmentTag = getBackStackEntryAt(backStackCount - 1).name
        return findFragmentByTag(fragmentTag)!!
    }

    return fragments.firstOrNull()
}

private fun FragmentManager.popBackStackIfSafe() {
    if (!isStateSaved) {
        popBackStack()
    }
}

private fun FragmentTransaction.commitSafely(fragmentManager: FragmentManager) {
    if (!fragmentManager.isStateSaved) {
        commit()
    } else {
        commitAllowingStateLoss()
    }
}