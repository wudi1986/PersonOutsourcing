package com.genimous.peopleoutsourcing.fragment;

import android.os.Bundle;
import android.view.View;

import com.genimous.peopleoutsourcing.activity.R;
import com.genimous.peopleoutsourcing.base.BaseFragment;
import com.genimous.peopleoutsourcing.widget.LoadStatusView;

/**
 * Created by wudi on 18/1/4.
 */

public class UserCenterFragment extends BaseFragment {
    LoadStatusView mLoadStatusView;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLoadStatusView = (LoadStatusView)view.findViewById(R.id.LoadStatusView_Discover_status);
        mLoadStatusView.setViewStatus(LoadStatusView.LoadStatus.NO_NET);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

    public static UserCenterFragment newInstance(Bundle bundle) {
        UserCenterFragment fragment = new UserCenterFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
