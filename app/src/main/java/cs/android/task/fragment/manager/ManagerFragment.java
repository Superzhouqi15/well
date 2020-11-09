package cs.android.task.fragment.manager;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cs.android.task.MyApplication;
import cs.android.task.R;
import cs.android.task.entity.Order;


import cs.android.task.view.main.MainActivity;
import task.ProfileOuterClass;

public class ManagerFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private List<Order> orderList;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private View view;
    private int userType;

    public ManagerFragment () {
        // Required empty public constructor
    }

    public static ManagerFragment newInstance() {
        ManagerFragment fragment = new ManagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public int getUserType(){
        return userType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userType = ((MainActivity)getActivity()).getType();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project, container, false);
//        myProfile = ((MainActivity)getActivity()).getMyProfile();
//        MyApplication myApplication = new MyApplication();
//        host = myApplication.getHost();
        CollapsingToolbarLayout collapsingToolbarLayout =
                view.findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#ffffff"));
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#e16b6b"));
        orderList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.projects_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(orderList, this);
        recyclerView.setAdapter(adapter);
        initProjectList();
        view.findViewById(R.id.watch)
                .setOnClickListener(this::onWatchClicked);
        adapter.notifyItemRangeInserted(0, 2);
        adapter.notifyDataSetChanged();
        int t = ((MainActivity)getActivity()).getType();
        Log.e("--->", "onCreateView: " + String.valueOf(t));


        return view;
    }

    // for debug
    private void initProjectList() {
        Order order1 = new Order();
        order1.setCreateDate(new Date());
        order1.setID("1");
        order1.setLocation("SCNU");

        Order order2 = new Order();
        order2.setCreateDate(new Date());
        order2.setID("2");
        order2.setLocation("SCNU");

        orderList.add(order1);
        orderList.add(order2);
    }


    public void onWatchClicked(View view) {
        Log.i("click", "watch");
        /*
        Bundle bundle = new Bundle();

        CreateDialog dialog = CreateDialog.newInstance(bundle);
        FragmentManager fm = getFragmentManager();
        assert fm != null;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(R.id.fragment_layout, dialog);
        transaction.addToBackStack(null);
        transaction.commit();
         */
    }

    public List<Order> getOrderList (){
        return this.orderList;
    }

    public OrderAdapter getAdapter(){
        return adapter;
    }
}
