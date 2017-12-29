package com.sinfeeloo.newtodo.listdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mhj
 * @date: 2017/10/9 17:11
 * @desc:
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    List<MainBean> mList = new ArrayList<>();
    Context mContext;
    int mView;
    private SparseBooleanArray mCheckStates = new SparseBooleanArray();
    private boolean selectAll;

    public MainAdapter(Context context, int layout, List<MainBean> datas) {
        this.mList = datas;
        this.mContext = context;
        this.mView = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(mView, parent,
                false), new MyCustomEditTextListener());
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position).getName());
        holder.cb.setTag(position);
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = (int) buttonView.getTag();
                mCheckStates.put(pos, isChecked);
                mList.get(pos).setChecked(mCheckStates.get(pos, false));
                if(!isChecked&&selectAll){
                    ((MainActivity)mContext).setNoSelectAll();
                }
            }
        });
        holder.cb.setChecked(mCheckStates.get(position, false));
        holder.myCustomEditTextListener.updatePosition(position);
        holder.et.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        CheckBox cb;
        EditText et;
        public MyCustomEditTextListener myCustomEditTextListener;

        public MyViewHolder(View view, MyCustomEditTextListener listener) {
            super(view);
            tv = view.findViewById(R.id.tv_main);
            cb = view.findViewById(R.id.cb_main);
            et = view.findViewById(R.id.et_main);
            myCustomEditTextListener = listener;
            et.addTextChangedListener(myCustomEditTextListener);
        }
    }


    public void selectAll(boolean b) {
        selectAll = b;
        for (int i = 0; i < mList.size(); i++) {
            mCheckStates.put(i, b);
            mList.get(i).setChecked(b);
        }
        notifyDataSetChanged();
    }


    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            mList.get(position).setContent(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }


}
