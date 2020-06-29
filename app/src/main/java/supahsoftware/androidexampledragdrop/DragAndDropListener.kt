package supahsoftware.androidexampledragdrop

import android.content.ClipDescription
import android.graphics.Color
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup

class DragAndDropListener : View.OnDragListener {
    override fun onDrag(view: View, event: DragEvent): Boolean {
        return when (event.action) {
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.setBackgroundColor("#7BE067"); true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.setBackgroundColor("#DEDEDE"); true
            }
            DragEvent.ACTION_DROP -> {
                val draggingView = event.localState as View
                val draggingViewParent = draggingView.parent as DragAndDropContainer
                draggingViewParent.removeContent(draggingView)

                val landingContainer = view as DragAndDropContainer
                landingContainer.setContent(draggingView)
                landingContainer.setBackgroundColor("#DEDEDE")
                true
            }
            DragEvent.ACTION_DRAG_STARTED -> event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            else -> true
        }
    }

    private fun View.setBackgroundColor(color: String) = setBackgroundColor(Color.parseColor(color))
}