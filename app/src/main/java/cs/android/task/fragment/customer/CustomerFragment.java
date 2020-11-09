package cs.android.task.fragment.customer;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cs.android.task.MyApplication;
import cs.android.task.R;
import cs.android.task.entity.Order;
import cs.android.task.view.main.MainActivity;
import task.ProfileOuterClass;

public class CustomerFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private View view;
    private ImageView imageView;

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
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> images = ImagePicker.getImages(data);
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            Log.i("---->", "uri: "+image.getUri().toString());
            Log.i("---->", "path: "+image.getPath().toString());
            Log.i("---->", "name: "+image.getName().toString());
            imageView.setImageURI(image.getUri());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        imageView = view.findViewById(R.id.reportImage);
        imageView.setOnClickListener(v ->{
            ImagePicker.create(this).folderMode(true).returnMode(ReturnMode.ALL).single().start();
        });
        return view;
    }

}
