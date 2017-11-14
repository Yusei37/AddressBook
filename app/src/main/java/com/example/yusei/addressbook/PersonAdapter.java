package com.example.yusei.addressbook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements View.OnClickListener{

    private List<Person> mPersonList;
    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View personView;
        TextView personName;

        public ViewHolder(View view) {
            super(view);
            personView = view;
            personName = (TextView) view.findViewById(R.id.person_name);
        }
    }

    public PersonAdapter(List<Person> personList) {
        mPersonList = personList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        holder.personName.setText(person.getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {      //getTag获取的即是点击位置
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
}
