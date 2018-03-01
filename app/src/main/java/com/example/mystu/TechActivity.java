package com.example.mystu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class TechActivity extends AppCompatActivity {

    private WebView webView;

    private Toolbar toolbar;

    private static int index=0;

    private static int imageResourceIndex=0;

    private static String[] text=new String[]{"信息门户","国际处","学院网站"};

    private static int[] imageResources =new int[]{
            R.drawable.ic_message,
            R.drawable.ic_worldweb,
            R.drawable.ic_myweb
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);
        toolbar=(Toolbar)findViewById(R.id.tech_toolbar);
        setSupportActionBar(toolbar);

        BoomMenuButton boomMenuButton=(BoomMenuButton)findViewById(R.id.tech_boom) ;
        webView=(WebView)findViewById(R.id.web_view_tech);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.canGoBack();
        webView.canGoForward();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://www.jwc.uestc.edu.cn/Index.action");

        TextOutsideCircleButton.Builder builderfirst=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent=new Intent(TechActivity.this,MainActivity.class);
                startActivity(intent);
            }
        })
                .normalImageRes(imageResources[0])
                .normalText(text[0]);
        boomMenuButton.addBuilder(builderfirst);

        TextOutsideCircleButton.Builder buildersecond=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intentsecond=new Intent(TechActivity.this,WebActivity.class);
                startActivity(intentsecond);
            }
        })
                .normalImageRes(imageResources[1])
                .normalText(text[1]);
        boomMenuButton.addBuilder(buildersecond);

        TextOutsideCircleButton.Builder builderthird=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intentsecond=new Intent(TechActivity.this,InsitActivity.class);
                startActivity(intentsecond);
            }
        })
                .normalImageRes(imageResources[2])
                .normalText(text[2]);
        boomMenuButton.addBuilder(builderthird);
    }

    @Override
    protected void onResume(){
        super.onResume();
        toolbar.setTitle("教务处");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.go_back) {
            webView.goBack();
            return true;
        } else if (id == R.id.refresh_page) {
            webView.reload();
            return true;
        } else if (id == R.id.clean_history) {
            webView.clearHistory();
            Toast.makeText(TechActivity.this, "历史记录已清除", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.clean_date) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(TechActivity.this);
            dialog.setTitle("注意：");
            dialog.setMessage("如果继续，将会清楚所有缓存数据，其中可能包含重要信息，是否继续？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    webView.clearFormData();
                    Toast.makeText(TechActivity.this, "缓存已清除", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(TechActivity.this, "操作取消", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();

            return true;
        } else if (id == R.id.find_history) {
            Intent intent=new Intent(TechActivity.this,SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
