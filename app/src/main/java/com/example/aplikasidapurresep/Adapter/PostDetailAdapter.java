package com.example.aplikasidapurresep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aplikasidapurresep.Model.Post;
import com.example.aplikasidapurresep.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailAdapter.ViewHolder>  {

    public Context mContext;
    public List<Post> mPost;

    private FirebaseUser firebaseUser;

    public PostDetailAdapter(Context mContext, List<Post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_detail_item, parent, false);
        return new PostDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final Post post = mPost.get(position);

        Glide.with(mContext).load(post.getPostimage())
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.post_image);

        holder.nama_masakan.setText(post.getName_receipt());
        holder.time_of_cook.setText(post.getWaktu_memasak());
        holder.description_s.setText(post.getDescription_receipt());


    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView post_image;
        TextView nama_masakan, time_of_cook, description_s, bahan, langkah;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
            nama_masakan = itemView.findViewById(R.id.nama_masakan);
            time_of_cook = itemView.findViewById(R.id.time_of_cook);
            description_s = itemView.findViewById(R.id.description_s);


        }
    }
}
