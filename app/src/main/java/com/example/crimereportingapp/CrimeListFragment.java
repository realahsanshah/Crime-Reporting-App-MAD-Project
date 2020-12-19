package com.example.crimereportingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private class CrimeHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView dateTextView;

        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));

            titleTextView=(TextView)itemView.findViewById(R.id.crime_item_title);
            dateTextView=(TextView)itemView.findViewById(R.id.crime_item_date);
        }

        public void bind(Crime crime){
            this.mCrime=crime;
            titleTextView.setText(mCrime.getTitle());
            dateTextView.setText(mCrime.getDate().toString());
        }
    }

    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> mCrimes) {
            this.mCrimes = mCrimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());

            return new CrimeHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime=mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }


    private RecyclerView crimesRecyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        crimesRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        crimesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI() {
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();

        crimeAdapter=new CrimeAdapter(crimes);

        crimesRecyclerView.setAdapter(crimeAdapter);
    }
}
