package com.example.simonpintado.recyclervcard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Pintado on 12/11/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String>names;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<String>names, int layout,OnItemClickListener listener){
        this.names=names;
        this.layout=layout;
        this.itemClickListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflado de vista y es pasada por el constructor del viewHolder
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //volcado de datos
        holder.bind(names.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextViewName;
        public ViewHolder(View itemView){
            super(itemView);
            this.TextViewName=(TextView) itemView.findViewById(R.id.textViewName); //pasando el view se extrae la referencia
        }

        public void bind(final String name, final OnItemClickListener listener ) { //se pasa el modelo
            this.TextViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(name,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String name,int position);
    }
}
