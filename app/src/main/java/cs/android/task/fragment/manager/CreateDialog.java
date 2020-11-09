package cs.android.task.fragment.manager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import java.util.List;

import androidx.fragment.app.Fragment;
import cs.android.task.R;

public class CreateDialog extends Fragment {

private int userType;
private View view;
private TextInputEditText itemID;
private TextInputEditText itemLoc;
private TextInputEditText itemNote;
private ImageView imageView;


public CreateDialog() {
    // Required empty public constructor
}

/**
 * Use this factory method to create a new instance of
 * this fragment using the provided parameters.
 *
 * @return A new instance of fragment ProfileFragment.
 */
// TODO: Rename and change types and number of parameters
public static CreateDialog newInstance(Bundle args) {
    CreateDialog fragment = new CreateDialog();
    fragment.setArguments(args);
    return fragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,
                         Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.item_creation_card, container, false);

    itemID = view.findViewById(R.id.item_id);
    itemLoc = view.findViewById(R.id.item_loc);
    itemNote = view.findViewById(R.id.item_mark);
    imageView = view.findViewById(R.id.choose_img);

    (view.findViewById(R.id.item_submit)).setOnClickListener(this::onClickSubmit);
    imageView.setOnClickListener(this::onChooseImage);

    return view;
}

public void onClickSubmit(View v) {
    Snackbar.make(getView(), "submited", Snackbar.LENGTH_LONG)
            .setAction("ok", (view) -> {
                this.getFragmentManager().popBackStack();
            })
            .show();
}

public void onChooseImage(View v) {
    ImagePicker.create(this).folderMode(true).returnMode(ReturnMode.ALL).single().start();
}

// add submit callback here
public void onItemSubmit(View view) {
    if (itemID.getText() == null) {
        Snackbar.make(getView(), "需要输入井盖编号", Snackbar.LENGTH_SHORT).show();
        return;
    }
    if (itemLoc.getText() == null) {
        Snackbar.make(getView(), "需要输入位置", Snackbar.LENGTH_SHORT).show();
        return;
    }

    String id = itemID.getText().toString();
    String loc = itemLoc.getText().toString();
    String mark = "";
    if (itemNote.getText() != null)
        mark = itemNote.getText().toString();
}

@Override
public void onActivityResult(int requestCode, final int resultCode, Intent data) {
    if ( ImagePicker.shouldHandle(requestCode, resultCode, data)) {
        // Get a list of picked images
        List<Image> images = ImagePicker.getImages(data);
        // or get a single image only
        Image image = ImagePicker.getFirstImageOrNull(data);
        android.util.Log.i("---->", "uri: "+image.getUri().toString());
        android.util.Log.i("---->", "path: "+image.getPath().toString());
        android.util.Log.i("---->", "name: "+image.getName().toString());
        imageView.setImageURI(image.getUri());
    }
    super.onActivityResult(requestCode, resultCode, data);
}


}
