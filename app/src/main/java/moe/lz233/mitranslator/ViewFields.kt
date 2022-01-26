package moe.lz233.mitranslator

import android.annotation.SuppressLint
import android.view.View
import android.widget.ScrollView
import android.widget.TextClock

@SuppressLint("ResourceType", "StaticFieldLeak")
object ViewFields {
    lateinit var rootView: ScrollView
    val textClock: TextClock by lazy { rootView.findViewById(2131296554) }
    val chatTranslateButton: View by lazy { rootView.findViewById(2131296531) }
    val faceToFaceTranslateButton: View by lazy { rootView.findViewById(2131296533) }
    val ocrTranslateButton: View by lazy { rootView.findViewById(2131296538) }
    val translateHistoryButton: View by lazy { rootView.findViewById(2131296535) }
    val exchangeCurrencyButton: View by lazy { rootView.findViewById(2131296532) }
    val eSimButton:View by lazy { rootView.findViewById(2131296560) }
    val messageButton:View by lazy { rootView.findViewById(2131296541) }
}