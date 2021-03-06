package com.genimous.peopleoutsourcing.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.genimous.core.util.DeviceUtil;
import com.genimous.core.util.PermissionsUtil;
import com.genimous.peopleoutsourcing.R;
import com.genimous.peopleoutsourcing.base.BaseActivity;
import com.genimous.peopleoutsourcing.base.BaseFragment;
import com.genimous.peopleoutsourcing.fragment.DiscoverFragment;
import com.genimous.peopleoutsourcing.fragment.InviteFragment;
import com.genimous.peopleoutsourcing.fragment.MakeMoneyFragment;
import com.genimous.peopleoutsourcing.fragment.UserCenterFragment;
import com.genimous.peopleoutsourcing.service.WindowChangeDetectingService;
import com.genimous.peopleoutsourcing.view.IconTitleItemView;

import java.util.ArrayList;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class MainActivity extends BaseActivity {

    PageBottomTabLayout tabLayout;
    ViewPager viewPager;
    private NavigationController navigationController;

    private BaseFragment[] mFragments = new BaseFragment[4];



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //检测用户是否打开权限
        PermissionsUtil.checkVersion(this);
        if(PermissionsUtil.checkAccessibility(this)){
            startWindowChangeDetectingService();
        }

        tabLayout = (PageBottomTabLayout)findViewById(R.id.PageBottomTabLayout_mainActivity);
        viewPager = (ViewPager)findViewById(R.id.ViewPager_mainActivity);
        initView();

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        Log.i("aaa","startActivityForResult requestCode == "+requestCode);

    }


    private void initView() {

        //初始化fragments
        mFragments[0] = MakeMoneyFragment.newInstance(new Bundle());
        mFragments[1] = InviteFragment.newInstance(new Bundle());
        mFragments[2] = DiscoverFragment.newInstance(new Bundle());
        mFragments[3] = UserCenterFragment.newInstance(new Bundle());

        // 初始化ViewPager
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    navigationController.setSelect(viewPager.getCurrentItem());
                }
            }
        });
        viewPager.setOffscreenPageLimit(3);

        // 初始化底部Tab
        navigationController = tabLayout.custom()
                .addItem(buildTabItem(R.drawable.img_tab_make_money_up, R.drawable.img_tab_make_money_checked, "赚钱"))
                .addItem(buildTabItem(R.drawable.img_tab_invite_up, R.drawable.img_tab_invite_checked, "邀请"))
                .addItem(buildTabItem(R.drawable.img_tab_discovery_up, R.drawable.img_tab_discovery_check, "发现"))
                .addItem(buildTabItem(R.drawable.img_tab_mine_up, R.drawable.img_tab_mine_checked, "我的"))
                .build();
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int prePosition) {
                viewPager.setCurrentItem(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }
    private BaseTabItem buildTabItem(int drawable, int checkedDrawable, String title) {
        IconTitleItemView iconTitleItemView = new IconTitleItemView(this);
        iconTitleItemView.initialize(drawable, checkedDrawable, title);
        return iconTitleItemView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DeviceUtil.MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS) {
            if (!DeviceUtil.hasPermission(MainActivity.this)) {
                //若用户未开启权限，则引导用户开启“Apps with usage access”权限
                startActivityForResult(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                        DeviceUtil.MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
            }
        } else if (requestCode == PermissionsUtil.PERMISSIONSUTIL_REQUEST_CODE){
            //打开试玩
            startWindowChangeDetectingService();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startWindowChangeDetectingService(){
        Intent intent = new Intent(this, WindowChangeDetectingService.class);
        startService(intent);
    }
}
