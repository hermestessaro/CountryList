package com.example.countrylist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylist.R
import com.example.countrylist.databinding.ItemCountryBinding
import com.example.countrylist.model.Country

class CountryListAdapter(
    var countries: ArrayList<Country>
): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountriesList: List<Country>){
        countries.clear()
        countries.addAll(newCountriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = DataBindingUtil.inflate<ItemCountryBinding>(LayoutInflater.from(parent.context), R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countries[position]
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root)
}