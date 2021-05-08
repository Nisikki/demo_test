package com.example.demo_test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.demo_test.utils.inflater2Binding

/**
 * 使用ViewBinding，使用的布局里面包含有include的话，如果是merge的话需要在setupMergeView方法里面对view进行bind，普通include需要使用id
 * @author Jowan
 * Created on 12/23/20.
 */
abstract class BaseVbFragment<VB : ViewBinding> : Fragment() {
    /**
     * include的布局需要自己加上id，如果包含merge的话，需要单独对对应的布局进行bind，
     * 比如merge_toolbar.xml
     * ```kotlin
     *  protected lateinit var mergeToolbar: MergeToolbarBinding
     *  mergeToolbar = MergeToolbarBinding.bind(_binding.root)
     * ```
     */
    private var _binding: VB? = null
    /**
     * 子类使用这个，当前基类使用[_binding]
     */
    protected val binding: VB
        get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflater2Binding(inflater, container, 0, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


    protected abstract fun initViewData()
}