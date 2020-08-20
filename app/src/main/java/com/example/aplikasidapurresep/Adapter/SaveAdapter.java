package com.example.aplikasidapurresep.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasidapurresep.Fragment.PostDetailFragment;
import com.example.aplikasidapurresep.Model.Post;
import com.example.aplikasidapurresep.R;

import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {
    private Context context;
    private List<Post> mPost;

    public SaveAdapter(Context context, List<Post> mPost) {
        this.context = context;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.save_item,parent,false);
        return new SaveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Post post = mPost.get(position);
        Glide.with(context).load(post.getPostimage()).into(holder.image_save_fav);

        holder.txt_save_fav.setText(post.getName_receipt());
        holder.txt_name.setText(post.getPublisher());
        holder.image_save_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("postid",post.getPostid());
                editor.apply();

                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PostDetailFragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_save_fav;
        TextView txt_save_fav;
        TextView txt_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_save_fav = itemView.findViewById(R.id.image_save_fav);
            txt_save_fav = itemView.findViewById(R.id.txt_save_fav);
            txt_name = itemView.findViewById(R.id.txt_name);
        }
    }
}
