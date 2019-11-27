package com.vikram.trendingrepos.ui.customcomponents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vikram.trendingrepos.databinding.ComponentLoadingBinding

class LoadingComponent(
    context: Context?,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    init {
        val binding = ComponentLoadingBinding.inflate(LayoutInflater.from(context),this,false)
        addView(binding.root)
        binding.shimmerViewContainer.startShimmer()
    }

}