package pl.szkolaandroida.newsviewer

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ChangeTopicDialogFragment : DialogFragment() {

    private var changeTopicListener: ChangeTopicListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        changeTopicListener = context as ChangeTopicListener
    }

    override fun onDetach() {
        super.onDetach()
        changeTopicListener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val editText = EditText(requireActivity())
        return AlertDialog.Builder(requireActivity())
            .setTitle("Select topic of the news")
            .setPositiveButton("Change") { _, _ ->
                val topic = editText.text.toString()
                changeTopicListener?.changeTopic(topic)
            }
            .setView(editText)
            .setNegativeButton("Cancel", null)
            .create()

    }

    interface ChangeTopicListener {
        fun changeTopic(topic: String)
    }
}