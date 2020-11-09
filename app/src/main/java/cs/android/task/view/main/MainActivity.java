package cs.android.task.view.main;


import android.content.Intent;
import android.os.Bundle;

import android.transition.Fade;
import android.view.Window;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.beloo.widget.chipslayoutmanager.util.log.Log;

import org.fusesource.mqtt.client.CallbackConnection;

import cs.android.task.MyApplication;
import cs.android.task.R;


import cs.android.task.fragment.Note.MyNoteFragment;
import cs.android.task.fragment.customer.CustomerFragment;
import cs.android.task.fragment.friend.FriendFragment;
import cs.android.task.fragment.profile.ProfileFragment;
import cs.android.task.fragment.manager.ManagerFragment;
import cs.android.task.fragment.schedule.ScheduleFragment;
import cs.android.task.view.Util;
import task.ProfileOuterClass;

public class MainActivity extends AppCompatActivity {

    private String myToken;
    private FriendFragment friendFragment;
    private CustomerFragment customerFragment;
    private ScheduleFragment scheduleFragment;
    private ProfileOuterClass.Profile myProfile;
    private static String host;
    private int port = 50050;
    public static final String TOPIC = "topic/test";
    private CallbackConnection connection;
    private MyNoteFragment myNoteFragement;


    public String getMyToken() {
        return myToken;
    }

    public ProfileOuterClass.Profile getMyProfile() {
        return myProfile;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 开启动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition((new Fade()).setDuration(300));

        setContentView(R.layout.activity_main);
        MyApplication myApplication = new MyApplication();
        host = myApplication.getHost();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int userType = bundle.getInt("userType");
        Log.e("----->", "onCreate: " + String.valueOf(userType) );

//        setMyToken(bundle.getString("token"));


        //获取profile
        /*
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext().build();
        ProfileServiceGrpc.ProfileServiceBlockingStub profileBlockingStub = ProfileServiceGrpc.newBlockingStub(channel);
        myProfile = profileBlockingStub.getProfile(Login.Token.newBuilder().setToken(myToken).build());

        Log.e("profile------------->", "onCreate: " + myProfile.getEmail() + myProfile.getName() + myProfile.getPhoneNum());

        channel.shutdown();
        * */

        customerFragment = CustomerFragment.newInstance();

        Util.immerseStatusBar(this);
        setupNavBar();
        loadFragment(customerFragment);
    }




    private void setupNavBar() {
        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        nav.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.project:
                            loadFragment(customerFragment);
                            return true;

                        case R.id.friend:
                            friendFragment = FriendFragment.newInstance();
                            loadFragment(friendFragment);
                            return true;
                        case R.id.my:
                            loadFragment(new ProfileFragment());
                            return true;
                        case R.id.myNote:
                            myNoteFragement = MyNoteFragment.newInstance();
                            loadFragment(myNoteFragement);
                            return true;
                        case R.id.schedule:
                            scheduleFragment = ScheduleFragment.newInstance();
                            loadFragment(scheduleFragment);
                            return true;
                        default:
                            return false;
                    }
                });
    }

    public FriendFragment getFriendFragment() {
        return friendFragment;
    }

    public ScheduleFragment getScheduleFragment() {
        return scheduleFragment;
    }
    public MyNoteFragment getMyNoteFragment() {
        return myNoteFragement;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
