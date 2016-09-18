package com.example.douglas.meuslivrosapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LivroAdapter extends BaseAdapter {

    Context context;
    List<Livro> items;

    List<Livro> selectedItems;

    public void setSelectedItems(List<Livro> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public LivroAdapter(Context context, List<Livro> items) {
        this.context = context;
        this.items = items;
        this.selectedItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.livro_item, parent, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Livro livro = (Livro)getItem(position);

        if (livro != null) {
            viewHolder.tvTitulo.setText(livro.getTitulo());
            viewHolder.tvAutor.setText(livro.getAutor());
        }

        if (selectedItems.contains(livro)) {
            convertView.setBackgroundResource(R.color.colorAccent);
        } else {
            convertView.setBackgroundResource(android.R.color.transparent);
        }

        return convertView;
    }

    static class ViewHolder {
        public final TextView tvTitulo, tvAutor;

        public ViewHolder(View v) {
            tvTitulo = (TextView) v.findViewById(R.id.tv_livro_titulo);
            tvAutor = (TextView) v.findViewById(R.id.tv_livro_autor);
        }
    }
}
