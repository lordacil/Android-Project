package ujikom.rizkynugraha.com.tiket_kuy;

import android.widget.Filter;

import java.util.ArrayList;

public class KustonFilter extends Filter {
    Adapters adapter;
    ArrayList<Transportasi> filterList;

    public KustonFilter(ArrayList<Transportasi> filterList, Adapters adapter)
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
            ArrayList<Transportasi> filteredTransportasi=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getKode().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredTransportasi.add(filterList.get(i));
                }
            }

            results.count=filteredTransportasi.size();
            results.values=filteredTransportasi;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, android.widget.Filter.FilterResults results) {

        adapter.transportasi= (ArrayList<Transportasi>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }
}
