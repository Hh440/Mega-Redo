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

public class RecyclerNovel_listAdapter extends FirebaseRecyclerAdapter<Novel_java,RecyclerNovel_listAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerNovel_listAdapter(@NonNull FirebaseRecyclerOptions<Novel_java> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerNovel_listAdapter.ViewHolder holder, int position, @NonNull Novel_java model) {
        holder.txt.setText(model.getName());
        Picasso.get().load(model.getUrl()).fit().into(holder.img);
        holder.book_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerframe,new Bookopen_frag(model.getName(),model.getUrl(),model.getDescription())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerNovel_listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;
        LinearLayout book_open;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.txtnovel);
            img=itemView.findViewById(R.id.novelimg);
            book_open=itemView.findViewById(R.id.bookopen);
        }
    }
}
