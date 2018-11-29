package com.example.android.cookinwithgas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {
    final private StepAdapter.StepAdapterOnClickHandler mClickHandler;
    private List<StepInfo> mSteps;

    public StepAdapter(StepAdapter.StepAdapterOnClickHandler stepAdapterOnClickHandler) {
        mClickHandler = stepAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public StepAdapter.StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int stepViewId = R.layout.step_list_content;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(stepViewId, parent, false);

        return new StepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.StepAdapterViewHolder holder, int position) {
        StepInfo mStep = mSteps.get(position);
        holder.stepNumber.setText(mStep.stepId);
        holder.stepTitle.setText(mStep.stepTitle);
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) {
            return 0;
        } else {
            return mSteps.size();
        }
    }

    public void setStepData(List<StepInfo> recipeSteps) {
        mSteps = recipeSteps;
        notifyDataSetChanged();
    }

    public interface StepAdapterOnClickHandler {
        void onClick(int position);
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stepNumber;
        final TextView stepTitle;

        public StepAdapterViewHolder(View view) {
            super(view);

            stepNumber = view.findViewById(R.id.tv_step_id);
            stepTitle = view.findViewById(R.id.tv_step_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int stepNumber = getAdapterPosition();
            mClickHandler.onClick(stepNumber);
        }
    }
}
