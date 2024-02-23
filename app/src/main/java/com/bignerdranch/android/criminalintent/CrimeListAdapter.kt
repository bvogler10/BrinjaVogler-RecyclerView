package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemPoliceCrimeBinding
import com.google.android.material.snackbar.Snackbar

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class PoliceCrimeHolder(
    private val binding: ListItemPoliceCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val contactPoliceButton: Button = binding.ContactPolice

    fun bind(crime: Crime) {
        binding.policeCrimeTitle.text = crime.title
        binding.policeCrimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        contactPoliceButton.setOnClickListener {
            Snackbar.make(itemView, "Contacting Police for ${crime.title}", Snackbar.LENGTH_SHORT).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val crimeView = 0
    private val policeCrimeView = 1

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) {
            policeCrimeView
        } else {
            crimeView
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == policeCrimeView) {
            // Create ViewHolder for crimes requiring police
            val binding = ListItemPoliceCrimeBinding.inflate(inflater, parent, false)// inflate the layout for crimes requiring police
            PoliceCrimeHolder(binding)
        } else {
            // Create ViewHolder for normal crimes
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            CrimeHolder(binding)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is PoliceCrimeHolder -> {
                holder.bind(crime)
            }
            is CrimeHolder -> {
                holder.bind(crime)
            }
        }
    }
    override fun getItemCount() = crimes.size
}
