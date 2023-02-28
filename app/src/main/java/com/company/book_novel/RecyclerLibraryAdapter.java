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

public class RecyclerLibraryAdapter extends FirebaseRecyclerAdapter<Lib_java,RecyclerLibraryAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerLibraryAdapter(@NonNull FirebaseRecyclerOptions<Lib_java> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerLibraryAdapter.ViewHolder holder, int position, @NonNull Lib_java model) {
        holder.txt.setText(model.getName());
        Picasso.get().load(model.getUrl()).fit().into(holder.img);
        holder.lib_openbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerframe,new Bookopen_frag(model.getName(),model.getUrl(),model.description)).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerLibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.library_layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;
        LinearLayout lib_openbook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.txt_lib);
            img=itemView.findViewById(R.id.lib_img);
            lib_openbook= itemView.findViewById(R.id.lib_bookopen);

        }
    }
}
