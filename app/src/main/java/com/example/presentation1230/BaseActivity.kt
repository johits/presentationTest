package com.example.presentation1230

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setFinishOnTouchOutside(false)
    }

//    // 백라이트 관리
//    private var powerManager: PowerManager? = null
//    private var wakeLockDim: PowerManager.WakeLock? = null
//    private var wakeLockTurnOn: PowerManager.WakeLock? = null
//
//    // 앱
//    val app: TppApplication by lazy {
//        application as TppApplication
//    }
//
//    private val activityResultPublisher: PublishSubject<ActivityResult>
//        get() = AppObservers.activityResultPublisher
//
//    //
//    // ----------- 범용 결제 지원 -----------
//    //
//    var cardStatus = CardStatus.CARD_READY
//        private set
//    var cardTrack2Val: ByteArray? = null
//        private set
//    private var nullableCardTrack2ValDisp: String? = null
//    private var cardIcData: Any? = null
//
//    val cardTrack2ValDisp: String
//        get() = if (nullableCardTrack2ValDisp == null) "" else nullableCardTrack2ValDisp!!
//
//    // D2mini는 일단 kiosk 기능 사용 금지
//    protected val isMultiDisplay
//        get() = CurrentDevice.currentDevice != CurrentDevice.D2Mini && displayManager.displays.size >= 2
//
//    private val displayManager get() = getSystemService(DISPLAY_SERVICE) as DisplayManager
//    protected val mainDisplay: Display get() = displayManager.displays.getOrNull(0) ?: throw Exception("no display index 0")
//    protected val oppositeDisplay: Display get() = displayManager.displays.getOrNull(1) ?: throw Exception("no display index 1")
//
//    fun onDummyClick(v: View?) {
//    }
//
//    open fun onCloseClick(v: View?) {
//        finish()
//    }
//
//    /**
//     * (UI THREAD)
//     * CARD 읽힌 상태를 업데이트한다.
//     */
//    open fun performCardStatusUpdate(
//        cardStatus: CardStatus,
//        cardTrack2Val: ByteArray?,
//        cardTrack2ValDisp: String?,
//        cardIcData: Any?
//    ) {
//        turnOnBackLight()
//        this.cardStatus = cardStatus
//        if (this.cardTrack2Val != null) {
//            this.cardTrack2Val = null
//        }
//
//        if (cardTrack2Val != null) {
//            this.cardTrack2Val = Arrays.copyOf(cardTrack2Val, cardTrack2Val.size)
//        }
//        if (nullableCardTrack2ValDisp != null) {
//            nullableCardTrack2ValDisp = null
//        }
//        if (cardTrack2ValDisp != null) {
//            nullableCardTrack2ValDisp = String(cardTrack2ValDisp.toCharArray())
//        }
//    }
//
//    fun performCardStatusRelease() {
//        cardTrack2Val = null
//        nullableCardTrack2ValDisp = null
//    }
//
//    fun performCardStatusToReady() {
//        performCardStatusUpdate(CardStatus.CARD_READY, null, "", null)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
//        window.addFlags(FLAG_KEEP_SCREEN_ON)
//        setFinishOnTouchOutside(false)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        window.addFlags(FLAG_KEEP_SCREEN_ON)
//        backLightHandler.sendEmptyMessageDelayed(MSG_BACKLIGHT_DIM, 1000L)
//        powerManager = getSystemService(POWER_SERVICE) as PowerManager
//    }
//
//    override fun onDestroy() {
//        performCardStatusToReady()
//        super.onDestroy()
//    }
//
//    override fun finish() {
//        backLightHandler.removeMessages(MSG_BACKLIGHT_DIM)
//        if (wakeLockDim?.isHeld == true) {
//            wakeLockDim?.release()
//            wakeLockDim = null
//        }
//
//        if (wakeLockTurnOn?.isHeld == true) {
//            wakeLockTurnOn?.release()
//            wakeLockTurnOn = null
//        }
//        super.finish()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val result = ActivityResult(requestCode, resultCode, data)
//        activityResultPublisher.onNext(result)
//    }
//
//    @SuppressLint("InvalidWakeLockTag")
//    private fun turnOnBackLight() {
//        if (powerManager == null) {
//            Timber.d("turnOnBackLight() mPM is null")
//            return
//        }
//
//        if (wakeLockTurnOn?.isHeld == true) {
//            wakeLockTurnOn?.release()
//            wakeLockTurnOn = null
//        } else {
//            Timber.d("turnOnBackLight() mWLTurnOn.isHeld() is false")
//        }
//        wakeLockTurnOn = powerManager?.newWakeLock(FLAG_KEEP_SCREEN_ON, "TURN_ON")
//        wakeLockTurnOn?.acquire(15000) // 15초
//    }
//
//    @SuppressLint("InvalidWakeLockTag")
//    private val backLightHandler: Handler = Handler {
//        when (it.what) {
//            MSG_BACKLIGHT_DIM -> {
//                window.clearFlags(FLAG_KEEP_SCREEN_ON)
//                if (powerManager == null) {
//                    Timber.d("ACTION_SCREEN_OFF mPM is null")
//                    return@Handler false
//                }
//
//                if (wakeLockDim?.isHeld == true) {
//                    wakeLockDim?.release()
//                    wakeLockDim = null
//                } else {
//                    Timber.d("ACTION_SCREEN_OFF mWLDim.isHeld() is false")
//                }
//                wakeLockDim = powerManager?.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "DIM")
//                wakeLockDim?.acquire(10 * 60 * 1000L /*10 minutes*/)
//            }
//        }
//        true
//    }
//
//    protected fun setFullScreen() {
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        window.decorView.setOnSystemUiVisibilityChangeListener {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//    }
//
//    companion object {
//        @JvmField
//        var START_FROM = "START_FROM"
//
//        @JvmField
//        var START_FROM_RECONNECT = 99
//        protected const val MSG_BACKLIGHT_DIM = 1
//
//        @JvmField
//        var REQUEST_CODE_IDX = 0
//    }
}
