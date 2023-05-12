package com.example.appleadd.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appleadd.databinding.ItemOnboardingBinding
import com.example.appleadd.model.OnBoard
import com.example.appleadd.utilse.loadImage

class OnBoardingAdapter(private val onClick: (OnBoard) -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

   private val data = arrayListOf(
        OnBoard(
            title = "Title 1 ",
            desc = "Desc 1 ",
            image = "http://clipart-library.com/image_gallery/357507.png"
        ),
        OnBoard(
            title = "Title 2 ",
            desc = "Desc 2 ",
            image = "http://clipart-library.com/image_gallery/357507.png"
        ),
        OnBoard(
            title = "Title 3 ",
            desc = "Desc 3 ",
            image = "http://clipart-library.com/image_gallery/357507.png"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    inner class OnBoardingViewHolder(private val biding: ItemOnboardingBinding) :
        ViewHolder(biding.root) {
        fun bind(onBoard: OnBoard) {
            biding.tvTitle.text = onBoard.title
            biding.tvDesc.text = onBoard.desc
            biding.ivBoard.loadImage(onBoard.image)

            biding.btnStart.setOnClickListener {
                onClick(onBoard)
            }
        }

    }
}