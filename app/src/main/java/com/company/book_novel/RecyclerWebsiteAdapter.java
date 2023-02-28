package com.company.book_novel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class RecyclerWebsiteAdapter extends FirebaseRecyclerAdapter<Website_java,RecyclerWebsiteAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerWebsiteAdapter(@NonNull FirebaseRecyclerOptions<Website_java> options) {
        super(options);
    }

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param
     */


    @Override
    protected void onBindViewHolder(@NonNull RecyclerWebsiteAdapter.ViewHolder holder, int position, @NonNull Website_java model) {
        holder.txtvw.setText(model.getName());
        Picasso.get().load(model.getUrl()).into(holder.img);
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerframe,new Novel_list(model.getName())).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public RecyclerWebsiteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.website_layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtvw;
        ImageView img;
        LinearLayout open;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvw=itemView.findViewById(R.id.tvwebsite);
            img=itemView.findViewById(R.id.imageview);
            open=itemView.findViewById(R.id.open);
        }
    }
}
