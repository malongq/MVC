package com.izk.mvc.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.izk.mvc.R;
import com.izk.mvc.model.Repository;

import java.util.List;

/**
 * Created by xzhang
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>{

    private Context mContext ;
    private List<Repository> repositoryList ;

    public RepositoryAdapter(Context context, List<Repository> repositoryList){
        this.mContext = context ;
        this.repositoryList = repositoryList ;
    }


    @Override
    public RepositoryAdapter.RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repo,parent,false) ;
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.RepositoryViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);
        holder.text_repo_title.setText(repository.getName());
        holder.text_repo_description.setText(repository.getDescription());
        holder.text_watchers.setText(mContext.getResources().getString(R.string.text_watchers,repository.getWatchers()));
        holder.text_stars.setText(mContext.getResources().getString(R.string.text_stars,repository.getStargazers_count()));
        holder.text_forks.setText(mContext.getResources().getString(R.string.text_forks,repository.getForks()));

    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder{

        public CardView card_view ;
        public TextView text_repo_title ;
        public TextView text_repo_description ;
        public TextView text_watchers ;
        public TextView text_stars ;
        public TextView text_forks ;

        public RepositoryViewHolder(View itemView) {
            super(itemView);

            card_view = (CardView) itemView.findViewById(R.id.card_view);
            text_repo_title = (TextView) itemView.findViewById(R.id.text_repo_title);
            text_repo_description = (TextView) itemView.findViewById(R.id.text_repo_description);
            text_watchers = (TextView) itemView.findViewById(R.id.text_watchers);
            text_stars = (TextView) itemView.findViewById(R.id.text_stars);
            text_forks = (TextView) itemView.findViewById(R.id.text_forks);


        }


    }
}
