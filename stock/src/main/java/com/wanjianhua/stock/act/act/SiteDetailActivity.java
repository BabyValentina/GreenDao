package com.wanjianhua.stock.act.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanjianhua.stock.R;
import com.wanjianhua.stock.act.base.BaseActivity;
import com.wanjianhua.stock.act.bean.SiteInfo;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * Created by wanjianhua on 2017/4/5.
 */

public class SiteDetailActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout linear_detail;
    private View childView, emtpylayout;
    private TextView tv_levelchild, tv_waveband;
    private SiteInfo siteInfo;
    private TextView name;
    private TextView tv_totalprice, tv_appreciate, tv_totalcount, tv_maxloser;
    private ImageView img_back;
    private int totalprice;
    private float price;//原始价格
    DecimalFormat decimalFormat = new DecimalFormat(".00");
    private String loser1_1, loser1_2, loser1_3;
    private String win1_1, win1_2, win1_3;
    private String win2_1, win2_2, win2_3;
    private String win3_1, win3_2, win3_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_detail);
        getIntentData();
        initView();
        setView();
        setLoser();
    }

    private void setLoser() {
        tv_maxloser.setText(win3_2 + "元");
    }

    private void setView() {
        tv_totalprice.setText(siteInfo.getTotalprice());
        tv_appreciate.setText("30%");
        name.setText(siteInfo.getName() + "(" + siteInfo.getCode() + ")");
        String tem = ((int) (totalprice / price)) + "";
        tv_totalcount.setText(tem.substring(0, tem.length() - 2) + "00");
    }

    private void getIntentData() {
        siteInfo = (SiteInfo) getIntent().getSerializableExtra("info");
        totalprice = Integer.parseInt(siteInfo.getTotalprice());
        price = Float.parseFloat(siteInfo.getSingleprice());
    }

    /**
     * 每一波段信息
     */
    private TextView tv_nowprice, tv_nowappreciate, singlecount, tv_singleprice, maxwin, nowtv_maxloser;
    private LinearLayout linear_top;

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        tv_totalprice = (TextView) findViewById(R.id.tv_totalprice);
        tv_appreciate = (TextView) findViewById(R.id.tv_appreciate);
        tv_totalcount = (TextView) findViewById(R.id.tv_totalcount);
        tv_maxloser = (TextView) findViewById(R.id.tv_maxloser);
        linear_detail = (LinearLayout) findViewById(R.id.linear_detail);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                childView = LayoutInflater.from(SiteDetailActivity.this).inflate(
                        R.layout.detail_child, null);
                tv_levelchild = (TextView) childView.findViewById(R.id.tv_level);
                tv_waveband = (TextView) childView.findViewById(R.id.tv_waveband);
                //==============================================================
                tv_nowprice = (TextView) childView.findViewById(R.id.tv_nowprice);
                linear_top = (LinearLayout) childView.findViewById(R.id.linear_top);
                tv_nowappreciate = (TextView) childView.findViewById(R.id.tv_nowappreciate);
                singlecount = (TextView) childView.findViewById(R.id.singlecount);
                tv_singleprice = (TextView) childView.findViewById(R.id.tv_singleprice);
                maxwin = (TextView) childView.findViewById(R.id.maxwin);
                nowtv_maxloser = (TextView) childView.findViewById(R.id.tv_nowmaxloser);
                //===============================================================
                linear_detail.addView(childView);
                if (i == 0) {
                    tv_levelchild.setText(getString(R.string.level1));
                    if (j == 0) {
                        //默认比例1:1:3
                        float nowprice = (float) (price * (1 - 0.033));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-3.3%");
                        int count = (int) (totalprice / 5 / 5 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        win1_1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        maxwin.setText((price - nowprice) * Integer.parseInt(overcount + "00") + "");
                        nowtv_maxloser.setText(0 + "元");
                        tv_waveband.setText(getString(R.string.wareband1));
                    } else if (j == 1) {
                        tv_waveband.setText(getString(R.string.wareband2));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.066));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-6.6%");
                        int count = (int) (totalprice / 5 / 5 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win1_2 = (Float.parseFloat(tem1) + Float.parseFloat(win1_1)) + "";
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        maxwin.setText(win1_2);
                        nowtv_maxloser.setText(win1_1 + "元");
                    } else if (j == 2) {
                        tv_waveband.setText(getString(R.string.wareband3));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.099));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-9.9%");
                        int count = (int) (totalprice / 5 / 5 * 3 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 * 3 + "");
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win1_3 = (Float.parseFloat(tem1) + Float.parseFloat(win1_2)) + "";
                        maxwin.setText(win1_3);
                        nowtv_maxloser.setText(win1_2 + "元");
                    }
                } else if (i == 1) {
                    tv_levelchild.setText(getString(R.string.level2));
                    if (j == 0) {
                        //默认比例1:1:3
                        float nowprice = (float) (price * (1 - 0.033));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-3.3%");
                        int count = (int) (totalprice / 5 / 5 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win2_1 = (Float.parseFloat(tem1) + Float.parseFloat(win1_3)) + "";
                        maxwin.setText(win2_1);
                        nowtv_maxloser.setText(win1_3 + "元");
                        tv_waveband.setText(getString(R.string.wareband1));
                    } else if (j == 1) {
                        tv_waveband.setText(getString(R.string.wareband2));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.066));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-6.6%");
                        int count = (int) (totalprice / 5 / 5 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win2_2 = (Float.parseFloat(tem1) + Float.parseFloat(win2_1)) + "";
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        maxwin.setText(win2_2);
                        nowtv_maxloser.setText(win2_1 + "元");
                    } else if (j == 2) {
                        tv_waveband.setText(getString(R.string.wareband3));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.099));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-9.9%");
                        int count = (int) (totalprice / 5 / 5 * 3 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 * 3 + "");
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win2_3 = (Float.parseFloat(tem1) + Float.parseFloat(win2_2)) + "";
                        maxwin.setText(win2_3);
                        nowtv_maxloser.setText(win2_2 + "元");

                    }
                } else if (i == 2) {
                    tv_levelchild.setText(getString(R.string.level3));
                    if (j == 0) {
                        tv_waveband.setText(getString(R.string.wareband1));
                        //默认比例1:1:3
                        float nowprice = (float) (price * (1 - 0.033));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-3.3%");
                        int count = (int) (totalprice / 5 / 5 * 3 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win3_1 = (Float.parseFloat(tem1) + Float.parseFloat(win2_3)) + "";
                        maxwin.setText(win3_1);
                        nowtv_maxloser.setText(win2_3 + "元");
                    } else if (j == 1) {
                        tv_waveband.setText(getString(R.string.wareband2));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.066));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-6.6%");
                        int count = (int) (totalprice / 5 / 5 * 3 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win3_2 = (Float.parseFloat(tem1) + Float.parseFloat(win3_1)) + "";
                        tv_singleprice.setText(totalprice / 5 / 5 + "");
                        maxwin.setText(win3_2);
                        nowtv_maxloser.setText(win3_1 + "元");
                    } else if (j == 2) {
                        tv_waveband.setText(getString(R.string.wareband3));
                        linear_top.setVisibility(View.GONE);
                        float nowprice = (float) (price * (1 - 0.099));
                        //构造方法的字符格式这里如果小数不足2位,会以0补足.
                        tv_nowprice.setText(decimalFormat.format(nowprice));
                        tv_nowappreciate.setText("-9.9%");
                        int count = (int) (totalprice / 5 / 5 * 3 * 3 / nowprice);
                        String temp = count + "";
                        String overcount = temp.substring(0, temp.length() - 2);
                        if (count < 100) {
                            singlecount.setText(count + getString(R.string.no100));
                        } else {
                            singlecount.setText(overcount + "00");
                        }
                        tv_singleprice.setText(totalprice / 5 / 5 * 3 + "");
                        String tem1 = (price - nowprice) * Integer.parseInt(overcount + "00") + "";
                        win3_3 = (Float.parseFloat(tem1) + Float.parseFloat(win3_2)) + "";
                        maxwin.setText(win3_3);
                        nowtv_maxloser.setText(win3_2 + "元");
                    }
                }
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
