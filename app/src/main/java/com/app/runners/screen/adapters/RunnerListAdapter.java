package com.app.runners.screen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.runners.R;
import com.app.runners.model.Runner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RunnerListAdapter extends RecyclerView.Adapter<RunnerListAdapter.RunnerItemHolder> {
    private List<Runner> mRunners;

    public RunnerListAdapter(List<Runner> runners) {
        mRunners = runners;
    }

    @Override
    public RunnerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new RunnerItemHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RunnerItemHolder holder, int position) {
        if(holder != null) {
            holder.bind(mRunners.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (null != mRunners ? mRunners.size() : 0);
    }

    public class RunnerItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.age)
        TextView mAge;
        @BindView(R.id.rank)
        TextView mRank;
        @BindView(R.id.time)
        TextView mTime;

        public RunnerItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Runner runner) {
            mName.setText(runner.getName());
            mAge.setText(String.valueOf(runner.getAge()));
            mTime.setText(String.valueOf(runner.getTime()));
            mRank.setText(String.valueOf(runner.getRank()));
        }
    }
}
