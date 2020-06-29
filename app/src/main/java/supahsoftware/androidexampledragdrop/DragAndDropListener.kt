package supahsoftware.androidexampledragdrop

import android.graphics.Color
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class DragAndDropListener : View.OnDragListener {
    override fun onDrag(view: View, event: DragEvent): Boolean {
        return when (event.action) {
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.setBackgroundColor(Color.parseColor("#00FF00"))
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.setBackgroundColor(Color.parseColor("#DEDEDE"))
                (view as? ImageView)?.clearColorFilter()
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                view.setBackgroundColor(Color.parseColor("#DEDEDE"))

                val draggingView = event.localState as View
                val draggingViewParent = draggingView.parent as ViewGroup
                draggingViewParent.removeView(draggingView)

                val landingContainer = view as DragAndDropContainer
                landingContainer.setContent(draggingView)
                true
            }
            else -> true
        }
    }
}