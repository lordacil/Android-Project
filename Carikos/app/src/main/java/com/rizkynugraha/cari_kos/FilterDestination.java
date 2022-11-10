package com.rizkynugraha.cari_kos;

import android.widget.Filter;

import com.rizkynugraha.cari_kos.util.AdapterDestination;

import java.util.ArrayList;

public class FilterDestination extends Filter {
    AdapterDestination adapter;
    ArrayList<Destination> filterList;

    public FilterDestination(ArrayList<Destination> filterList, AdapterDestination adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Destination> filteredDestination=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getDestination().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredDestination.add(filterList.get(i));
                }
            }

            results.count=filteredDestination.size();
            results.values=filteredDestination;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.destination= (ArrayList<Destination>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }
}
