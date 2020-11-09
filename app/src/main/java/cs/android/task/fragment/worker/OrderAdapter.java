package cs.android.task.fragment.worker;

import com.google.android.material.button.MaterialButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import cs.android.task.MyApplication;
import cs.android.task.R;
import cs.android.task.entity.Order;
import task.ProfileOuterClass;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private WorkerFragment workerFragment;
    private List<Order> orders;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("MM月dd日", Locale.CHINA);

    private MaterialButton sendBtn;
    private MaterialButton delBtn;
    private String host ;
    private int port = 50050;
    private ProfileOuterClass.Profile myProfile;



    public OrderAdapter (List<Order> orders,WorkerFragment workerFragment,ProfileOuterClass.Profile myProfile) {
        this.orders = orders;
        this.workerFragment = workerFragment;
        this.myProfile = myProfile;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView location;
        private TextView createDate;
        private TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.order_id);
            createDate = (TextView) itemView.findViewById(R.id.order_created_date);
            location = (TextView) itemView.findViewById(R.id.order_location);
            sendBtn = (MaterialButton) itemView.findViewById(R.id.order_send_btn);
            delBtn = (MaterialButton) itemView.findViewById(R.id.order_del_btn);
            MyApplication myApplication = new MyApplication();
            host = myApplication.getHost();
            delBtn.setText("完成");
            sendBtn.setText("查看");
        }



        private void delBtn(View view) {

            /*
            完成按钮
             */
            OrderAdapter.this.orders.remove(this.getAdapterPosition());
            OrderAdapter.this.notifyItemRemoved(this.getAdapterPosition());
        }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.location.setText(orders.get(position).getLocation());
        holder.createDate.setText(dateFormater.format(orders.get(position).getCreateDate()));
        holder.id.setText(orders.get(position).getID());




        delBtn.setOnClickListener(v->{
            Order order = orders.get(position);
            Log.i("click delbtn", position +  " " );
            Log.i("click delbtn", order.getID() +  " " );

            // RPC call
            /*
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext().build();
            ProjectServiceGrpc.ProjectServiceBlockingStub blockingStub = ProjectServiceGrpc.newBlockingStub(channel);
            ProjectOuterClass.ProjectQuery projectQuery = ProjectOuterClass.ProjectQuery.newBuilder()
                    .setToken(myProfile.getToken())
                    .setID(project.getId())
                    .build();

            ProjectOuterClass.Project delProject = blockingStub.getProjectInfo(projectQuery);
            Login.Result result = blockingStub.deleteProject(delProject);
            channel.shutdown();
            if(result.getSuccess()){
                Toast.makeText(projectFragment.getContext(),"Del project success",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(projectFragment.getContext(),"Del project fail",Toast.LENGTH_LONG).show();
            }
            */
            OrderAdapter.this.orders.remove(holder.getAdapterPosition());
            OrderAdapter.this.notifyItemRemoved(holder.getAdapterPosition());

        });

        sendBtn.setOnClickListener(v->{
            Order order = orders.get(position);

            FragmentManager fm = workerFragment.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

            // open detail dialog fragment
            /*
            DetailsFragment detailsFragment = DetailsFragment.newInstance();
            Bundle args = new Bundle();

            args.putString("orderId", order.getID());
            args.putString("orderLocation", order.getLocation());
            args.putString("orderCreateDate", order.getCreateDate().toString());
            detailsFragment.setArguments(args);

            transaction.add(R.id.fragment_layout, detailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
             */
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
