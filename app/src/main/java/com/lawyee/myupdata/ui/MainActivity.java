package com.lawyee.myupdata.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lawyee.myupdata.R;

import ezy.boost.update.UpdateAgent;
import ezy.boost.update.UpdateInfo;
import ezy.boost.update.UpdateManager;
import ezy.boost.update.UpdateUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String mCheckUrl = "http://client.waimai.baidu.com/message/updatetag";
    String mUpdateUrl = "http://mobile.ac.qq.com/qqcomic_android.apk";
    String mUpdateUrls = "http://gdown.baidu.com/data/wisegame/edab8e4f4a558844/sougoushurufa_640.apk";
    private TextView mCheckUpdate;
    private TextView mCheckUpdateCantIgnore;
    private TextView mCheckUpdateForce;
    private TextView mCheckUpdateSilent;
    private TextView mCheckUpdateNoNewer;
    private TextView mClean;
    private Button mBtnCeshi;
    private Button mBtnIntent;
    private LinearLayout mActivityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.check_update:
                check(true, true, false, false, true, 998);
                break;
            case R.id.check_update_cant_ignore:
                check(true, true, false, false, false, 998);
                break;
            case R.id.check_update_force:
                check(true, true, true, false, true, 998);
                break;
            case R.id.check_update_no_newer:
                check(true, false, false, false, true, 998);
                break;
            case R.id.check_update_silent:
                check(true, true, false, true, true, 998);
                break;
            case R.id.clean:
                UpdateUtil.clean(this);
                Toast.makeText(MainActivity.this, "cleared", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    void check(boolean isManual, final boolean hasUpdate, final boolean isForce, final boolean isSilent, final boolean isIgnorable, final int notifyId) {
        UpdateManager.create(this).setUrl(mCheckUrl).setManual(isManual).setNotifyId(notifyId).setParser(new UpdateAgent.InfoParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent = "• 支持文字、贴纸、背景音乐，尽情展现欢乐气氛；\n• 两人视频通话支持实时滤镜，丰富滤镜，多彩心情；\n• 图片编辑新增艺术滤镜，一键打造文艺画风；\n• 资料卡新增点赞排行榜，看好友里谁是魅力之王。";
                info.versionCode = 587;
                info.versionName = "v5.8.7";
                info.url = mUpdateUrl;
                info.md5 = "56cf48f10e4cf6043fbf53bbbc4009e3";
                info.size = 10149314;
                info.isForce = isForce;
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }


    private void initView() {
        mCheckUpdate = (TextView) findViewById(R.id.check_update);
        mCheckUpdate.setOnClickListener(this);
        mCheckUpdateCantIgnore = (TextView) findViewById(R.id.check_update_cant_ignore);
        mCheckUpdateCantIgnore.setOnClickListener(this);
        mCheckUpdateForce = (TextView) findViewById(R.id.check_update_force);
        mCheckUpdateForce.setOnClickListener(this);
        mCheckUpdateSilent = (TextView) findViewById(R.id.check_update_silent);
        mCheckUpdateSilent.setOnClickListener(this);
        mCheckUpdateNoNewer = (TextView) findViewById(R.id.check_update_no_newer);
        mClean = (TextView) findViewById(R.id.clean);
        mBtnCeshi = (Button) findViewById(R.id.btn_ceshi);
        mBtnIntent = (Button) findViewById(R.id.btn_intent);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
       mClean.setOnClickListener(this);
        mBtnCeshi.setOnClickListener(this);
        mBtnIntent.setOnClickListener(this);
    }
}
