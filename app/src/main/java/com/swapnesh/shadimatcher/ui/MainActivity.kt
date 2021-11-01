package com.swapnesh.shadimatcher.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.swapnesh.shadimatcher.R
import com.swapnesh.shadimatcher.model.UserListResponce
import com.swapnesh.shadimatcher.model.UserRoom
import com.swapnesh.shadimatcher.viewmodels.MainViewModel
import com.triologic.jewel_cliq.repository.ListRepository
import com.yuyakaido.android.cardstackview.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(),CardStackListener {

    private lateinit var layoutManager: CardStackLayoutManager
    private lateinit var mainVm: MainViewModel

    private val userList: ArrayList<UserListResponce.Result> = ArrayList()
    private val userData: ArrayList<UserRoom> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        mainVm.list()
       var  cardStackView = findViewById<CardStackView>(R.id.card_stack_view)

        ListRepository.initializeDB(this@MainActivity)

        val listObserver = Observer<UserListResponce> { list ->

            try {
                if(list!=null){
                    userList.addAll(list.results)
                    Log.d("list", userList.size.toString())
                    for (i in 0 until list.results.size) {
                      userData.add(UserRoom(list.results.get(i).name.title,list.results.get(i).name.first,list.results.get(i).name.last,
                          list.results.get(i).location.city,
                          list.results.get(i).dob.age.toString(),list.results.get(i).picture.large,
                          list.results.get(i).picture.medium,list.results.get(i).picture.thumbnail,""))

                        ListRepository.insertData(this@MainActivity,list.results.get(i).name.title,list.results.get(i).name.first,list.results.get(i).name.last,
                            list.results.get(i).location.city,
                            list.results.get(i).dob.age.toString(),list.results.get(i).picture.large,
                            list.results.get(i).picture.medium,list.results.get(i).picture.thumbnail,"")
                    }
                    layoutManager = CardStackLayoutManager(this, this).apply {
                        setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
                        setOverlayInterpolator(LinearInterpolator())
                    }

                    cardStackView.layoutManager = layoutManager
                    cardStackView.adapter = CardStackAdapter(userData)
                    cardStackView.itemAnimator.apply {
                        if (this is DefaultItemAnimator) {
                            supportsChangeAnimations = false
                        }
                    }
                }else{

                    userData.addAll(ListRepository.initializeDB(this@MainActivity).UserDao().getAll())
                    Log.d("list---database", userData.toString())


                    layoutManager = CardStackLayoutManager(this, this).apply {
                        setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
                        setOverlayInterpolator(LinearInterpolator())
                    }

                    cardStackView.layoutManager = layoutManager
                    cardStackView.adapter = CardStackAdapter(userData)
                    cardStackView.itemAnimator.apply {
                        if (this is DefaultItemAnimator) {
                            supportsChangeAnimations = false
                        }
                    }
                }



            } catch (e: Exception) {
            }


        }

        mainVm.mllist.observe(this, listObserver)



        actionButton(cardStackView,userData)


    }

    private fun actionButton(cardStackView: CardStackView, userData: ArrayList<UserRoom>) {
        val skip = findViewById<View>(R.id.skip_button)
        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            layoutManager.setSwipeAnimationSetting(setting)

            cardStackView.swipe()
          var position =   layoutManager.getTopPosition()
            Log.d("list","skip"+ layoutManager.getTopPosition())
            Toast.makeText(this, "Rejected", Toast.LENGTH_SHORT).show()
            ListRepository.initializeDB(this).UserDao().update(userData.get(position).first,"reject")
        }

        val rewind = findViewById<View>(R.id.rewind_button)
        rewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            layoutManager.setRewindAnimationSetting(setting)
            cardStackView.rewind()
        }

        val like = findViewById<View>(R.id.like_button)
        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            layoutManager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
            var position =   layoutManager.getTopPosition()
            Toast.makeText(this, "Accept", Toast.LENGTH_SHORT).show()
            ListRepository.initializeDB(this).UserDao().update(userData.get(position).first,"accept")
        }
    }





    override fun onCardDragging(direction: Direction?, ratio: Float) {


        if(direction ==Direction.Left){

        }else  if(direction ==Direction.Right){
          //  Toast.makeText(this, ""+direction, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCardSwiped(direction: Direction?) {
      //  Toast.makeText(this, "onCardSwiped"+direction, Toast.LENGTH_LONG).show()

        if(direction ==Direction.Left){

        }else{

        }
    }

    override fun onCardRewound() {
      //  Toast.makeText(this, "onCardRewound", Toast.LENGTH_LONG).show()
    }

    override fun onCardCanceled() {
     //   Toast.makeText(this, "onCardCanceled", Toast.LENGTH_LONG).show()
    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }
}