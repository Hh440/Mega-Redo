package com.company.book_novel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerBook_openAdapter extends FirebaseRecyclerAdapter<Book_chapter,RecyclerBook_openAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerBook_openAdapter(@NonNull FirebaseRecyclerOptions<Book_chapter> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerBook_openAdapter.ViewHolder holder, int position, @NonNull Book_chapter model) {
        holder.txt_chapter.setText(model.getCname());
        holder.chpater_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerframe,new Chapter_display(model.getCurl())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerBook_openAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_chapter;
        LinearLayout chpater_lay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_chapter=itemView.findViewById(R.id.txtchapternumber);
            chpater_lay=itemView.findViewById(R.id.show_listchapter);

        }
    }
}
