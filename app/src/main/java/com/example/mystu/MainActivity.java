package com.example.mystu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private Toolbar toolbar;

    private ProgressBar progress;

    private static int index=0;

    private static int imageResourceIndex=0;

    private static String[] text=new String[]{"教务处","国际处","学院网站"};

    private static int[] imageResources =new int[]{
            R.drawable.ic_techweb,
            R.drawable.ic_worldweb,
            R.drawable.ic_myweb
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progress=(ProgressBar)findViewById(R.id.progress_bar_main) ;

        BoomMenuButton boomMenuButton=(BoomMenuButton)findViewById(R.id.boom) ;
        webView=(WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.canGoBack();
        webView.canGoForward();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://idas.uestc.edu.cn/authserver/login?service=http%3A%2F%2Fportal.uestc.edu.cn%2F");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view,int newProgress){
                if(newProgress<100){
                   // String progress=newProgress+"%";
                    progress.setVisibility(view.VISIBLE);
                    progress.setProgress(newProgress);
                }else {
                    progress.setVisibility(view.GONE);

                }
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*for(int i=0;i<boomMenuButton.getPiecePlaceEnum().pieceNumber();i++){
            TextOutsideCircleButton.Builder builder=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener(){
                @Override
                public void onBoomButtonClick(int index){
                    Toast.makeText(MainActivity.this,"Clicked"+index,Toast.LENGTH_SHORT).show();
                }
            })
                    .normalImageRes(getImageResource())
                    .normalText(getext());
            boomMenuButton.addBuilder(builder);
        }*/
        TextOutsideCircleButton.Builder builderfirst=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent=new Intent(MainActivity.this,TechActivity.class);
                startActivity(intent);
            }
        })
                .normalImageRes(imageResources[0])
                .normalText(text[0]);
        boomMenuButton.addBuilder(builderfirst);

        TextOutsideCircleButton.Builder buildersecond=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intentsecond=new Intent(MainActivity.this,WebActivity.class);
                startActivity(intentsecond);
            }
        })
                .normalImageRes(imageResources[1])
                .normalText(text[1]);
        boomMenuButton.addBuilder(buildersecond);

        TextOutsideCircleButton.Builder builderthird=new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent=new Intent(MainActivity.this,InsitActivity.class);
                startActivity(intent);
            }
        })
                .normalImageRes(imageResources[2])
                .normalText(text[2]);
        boomMenuButton.addBuilder(builderthird);

    }

    static String getext(){
        if(index>=text.length)index=0;
        return text[index++];
    }
    static int getImageResource(){
        if(imageResourceIndex>=imageResources.length)
            imageResourceIndex=0;
        return imageResources[imageResourceIndex++];
    }



    @Override
    protected void onResume(){
        super.onResume();
        toolbar.setTitle("信息门户");
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
        }else if(id==R.id.refresh_page){
            webView.reload();
            return true;
        }else if(id==R.id.clean_history){
            webView.clearHistory();
            Toast.makeText(MainActivity.this,"历史记录已清除",Toast.LENGTH_SHORT).show();
            return true;
        }else if(id==R.id.clean_date){
            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("注意：");
            dialog.setMessage("如果继续，将会清楚所有缓存数据，其中可能包含重要信息，是否继续？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    webView.clearFormData();
                    Toast.makeText(MainActivity.this,"缓存已清除",Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"操作取消",Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();

            return true;
        }else if(id==R.id.find_history){
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
