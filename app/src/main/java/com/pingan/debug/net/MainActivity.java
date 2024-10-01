package com.pingan.debug.net;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pingan.debug.net.downdemo.DownloadActivity;
import com.pingan.debug.net.weather.WeatherActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.function_list) RecyclerView functionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<FunctionItem> functionItems = new ArrayList<>();
        init(functionItems);
        functionView.setAdapter(new FunctionAdapter(functionItems));
    }

    // 添加功能
    private void init(List<FunctionItem> functionItems){
        functionItems.add(new FunctionItem(WeatherActivity.class, "请求以及切换 baseUrl 的使用"));
        functionItems.add(new FunctionItem(DownloadActivity.class, "下载管理"));
    }
}
