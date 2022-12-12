package com.crystal.android.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.crystal.android.bottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    // 데이터 바인딩
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // BottomSheet layout 변수
    private val bottomSheetLayout by lazy { findViewById<LinearLayout>(R.id.bottom_sheet_layout) }
    private val bottomSheetExpandPersistentButton by lazy { findViewById<Button>(R.id.expand_persistent_button) }
    private val bottomSheetHidePersistentButton by lazy { findViewById<Button>(R.id.hide_persistent_button) }
    private val bottomSheetShowModalButton by lazy { findViewById<Button>(R.id.show_modal_button) }

    // bottomSheetBehavior
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initializePersistentBottomSheet()
        persistentBottomSheetEvent()

    }

    override fun onResume() {
        super.onResume()

        binding.showButton.setOnClickListener {
            // BottomSheet의 peek_height만큼 보여주기
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    // Persistent BottomSheet 초기화
    private fun initializePersistentBottomSheet() {

        // BottomSheetBehavior에 layout 설정
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                // BottomSheetBehavior state에 따른 이벤트
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d("MainActivity", "state: hidden")
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("MainActivity", "state: expanded")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("MainActivity", "state: collapsed")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("MainActivity", "state: dragging")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d("MainActivity", "state: settling")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d("MainActivity", "state: half expanded")
                    }
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })

    }

    // PersistentBottomSheet 내부 버튼 click event
    private fun persistentBottomSheetEvent() {

        bottomSheetExpandPersistentButton.setOnClickListener {
            // BottomSheet의 최대 높이만큼 보여주기
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        bottomSheetHidePersistentButton.setOnClickListener {
            // BottomSheet 숨김
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetShowModalButton.setOnClickListener {
            // 추후 modal bottomSheet 띄울 버튼
        }

    }


}