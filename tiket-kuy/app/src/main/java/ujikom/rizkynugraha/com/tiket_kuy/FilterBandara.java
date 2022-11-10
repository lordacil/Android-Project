package ujikom.rizkynugraha.com.tiket_kuy;


import android.support.v7.app.AppCompatActivity;
import android.widget.Filter;

import java.util.ArrayList;

public class FilterBandara extends Filter {
    AdapterBandara adapter;
    ArrayList<Bandara> filterList;

    public FilterBandara(ArrayList<Bandara> filterList, AdapterBandara adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected android.widget.Filter.FilterResults performFiltering(CharSequence constraint) {
        android.widget.Filter.FilterResults results=new android.widget.Filter.FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Bandara> filteredBandara=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNameBandara().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredBandara.add(filterList.get(i));
                }
            }

            results.count=filteredBandara.size();
            results.values=filteredBandara;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, android.widget.Filter.FilterResults results) {

        adapter.bandara= (ArrayList<Bandara>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }

}
