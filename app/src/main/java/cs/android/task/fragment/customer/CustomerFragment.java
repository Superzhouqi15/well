package cs.android.task.fragment.customer;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cs.android.task.MyApplication;
import cs.android.task.R;
import cs.android.task.entity.Order;
import cs.android.task.fragment.worker.OrderAdapter;
import cs.android.task.view.main.MainActivity;
import task.ProfileOuterClass;

public class CustomerFragment extends Fragment {

// TODO: Rename and change types of parameters
private View view;

public CustomerFragment () {
    // Required empty public constructor
}

public static CustomerFragment newInstance() {
    CustomerFragment fragment = new CustomerFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_customer, container, false);
    return view;
}

}

