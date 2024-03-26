package com.example.tasks;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TaskViewHolder> {

    private Context context;
    private List<Task> taskList;

    public MyAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.task_row,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskList.get(position);

        holder.task.setText(currentTask.getTask());
        holder.details.setText(currentTask.getDetails());
        holder.name.setText(currentTask.getUserName());

        String imageUrl = currentTask.getImageURL();

        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(currentTask.getTimeAdded().getSeconds() * 1000);
        holder.dateAdded.setText(timeAgo);

        Glide.with(context)
                .load(imageUrl)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        public TextView task,details,dateAdded,name;
        public ImageView image, shareButton;
        public String userId,username;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task=itemView.findViewById(R.id.task_title_list);
            details=itemView.findViewById(R.id.task_details);
            dateAdded=itemView.findViewById(R.id.task_timestamp_list);
            name=itemView.findViewById(R.id.task_row_username);
            image=itemView.findViewById(R.id.task_image_list);
            shareButton=itemView.findViewById(R.id.image_row_share_button);

            shareButton.setOnClickListener(v->{

            });
        }
    }



}
