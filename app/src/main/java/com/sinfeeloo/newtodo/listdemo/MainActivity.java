package com.sinfeeloo.newtodo.listdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MainBean> mList = new ArrayList<>();
    private MainAdapter mainAdapter;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        checkBox = (CheckBox) findViewById(R.id.cb_sellect_all);
        mainAdapter = new MainAdapter(this, R.layout.item_main, mList);
        mRecyclerView.setAdapter(mainAdapter);
        for (int i = 0; i <= 50; i++) {
            mList.add(new MainBean("条目" + i));
        }
        mainAdapter.notifyDataSetChanged();


//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mainAdapter.selectAll(b);
//            }
//        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAdapter.selectAll(checkBox.isChecked());
            }
        });



        findViewById(R.id.bt_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder ids = new StringBuilder();
                for (MainBean bean : mList) {
                    if (bean.isChecked()) {
                        ids.append(bean.getContent() + ";");
                    }
                }
                Log.e("mhj", "选中的条目：" + ids.toString());
                Toast.makeText(getApplicationContext(), ids.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setNoSelectAll(){
        checkBox.setChecked(false);
    }
}
