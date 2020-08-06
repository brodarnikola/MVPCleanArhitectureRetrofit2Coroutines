package com.vjezba.mvpcleanarhitecturegithub.presentation.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.presentation.activities.RepositoriesActivity
import kotlinx.android.synthetic.main.dialog_search_repository.*


@SuppressLint("ValidFragment")
class SearchRepositoryDialog constructor(val repositoryActivty: RepositoriesActivity)  : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_search_repository, container, false)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog?.setCanceledOnTouchOutside(false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stars.isChecked = true
        asc.isChecked = true

        btnConfirm.setOnClickListener {

            if( etInsertText.text.toString().isNotEmpty() ) {

                val idSort: Int = radioSort.checkedRadioButtonId
                val idOrder: Int = radioOrder.checkedRadioButtonId
                // If any radio button checked from radio group
                if (idSort != -1 && idOrder != -1) {

                    val sort = when {
                        resources.getResourceEntryName(radioSort.checkedRadioButtonId) == "stars" -> "stars"
                        resources.getResourceEntryName(radioSort.checkedRadioButtonId) == "forks" -> "forks"
                        else -> "updated"
                    }

                    val order = when {
                        resources.getResourceEntryName(radioOrder.checkedRadioButtonId) == "asc" -> "asc"
                        else -> "desc"
                    }

                    repositoryActivty.startSearch(etInsertText.text.toString(), sort, order)
                    dismiss()
                }
            }
            else {
                Toast.makeText(requireContext(), "Keyword can not be empty, because query to find repositories will not work", Toast.LENGTH_LONG).show()
            }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}