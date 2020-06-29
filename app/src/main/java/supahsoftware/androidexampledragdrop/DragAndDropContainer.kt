package supahsoftware.androidexampledragdrop

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class DragAndDropContainer(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val dragAndDropListener by lazy { DragAndDropListener() }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setOnDragListener(dragAndDropListener)
        if (childCount > 1) {
            throw IllegalStateException("There should be exactly 1 child inside of a DragAndDropContainer, but there was $childCount")
        } else if (childCount == 1) {
            getChildAt(0).setOnLongClickListener { startDrag(it) }
        }
    }

    fun setContent(view: View) {
        removeAllViews()
        addView(view)
    }

    private fun startDrag(view: View): Boolean {
        val tag = view.tag as? CharSequence
        val item = ClipData.Item(tag)
        val data = ClipData(tag, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
        val shadow = DragShadowBuilder(view)

        if (Build.VERSION.SDK_INT >= 24) {
            view.startDragAndDrop(data, shadow, view, 0)
        } else {
            view.startDrag(data, shadow, view, 0)
        }
        return true
    }
}