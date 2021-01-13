package com.example.crimereportingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class CrimeListFragment extends Fragment {

    private final static String SUBTITLE_VISIBLE = "subtitle_visible";
    private boolean mSubtitleVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        crimesRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null)
            mSubtitleVisible = savedInstanceState.getBoolean(SUBTITLE_VISIBLE);

        updateSubtitle();

        setHasOptionsMenu(true);

        updateUI();

        return view;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SUBTITLE_VISIBLE, mSubtitleVisible);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_crime_list, menu);
        MenuItem item = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible)
            item.setTitle(R.string.hide_subtitle);
        else
            item.setTitle(R.string.show_subtitle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;

            case R.id.show_subtitle:
                try {
                    mSubtitleVisible = !mSubtitleVisible;
                    Objects.requireNonNull(getActivity()).invalidateOptionsMenu();
                    updateSubtitle();
                    return true;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        try {
            CrimeLab crimeLab = CrimeLab.get(getActivity());
            int count = crimeLab.getCrimes().size();

            String subtitle = getString(R.string.subtitle_format, count);

            if (!mSubtitleVisible)
                subtitle = null;

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportActionBar().setSubtitle(subtitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimes);
            crimesRecyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.setCrimes(crimes);
            crimeAdapter.notifyDataSetChanged();

        }
    }


    private RecyclerView crimesRecyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleTextView;
        private final TextView dateTextView;
        private final ImageView imageView;


        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
            super(inflater.inflate(viewType, parent, false));

            titleTextView = itemView.findViewById(R.id.crime_item_title);
            dateTextView = itemView.findViewById(R.id.crime_item_date);
            imageView = itemView.findViewById(R.id.is_solved_image);

            itemView.setOnClickListener(this);
        }

        public void bind(Crime crime) {
            this.mCrime = crime;
            titleTextView.setText(mCrime.getTitle());
            dateTextView.setText(mCrime.getDate().toString());
            imageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
        }


        @Override
        public void onClick(View v) {

            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> mCrimes) {
            this.mCrimes = mCrimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(inflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            Crime crime = mCrimes.get(position);
            if (crime.isRequirePolice() && !crime.isSolved())
                return R.layout.serious_list_item_crime;
            else
                return R.layout.list_item_crime;
        }

        public void setCrimes(List<Crime> crimes) {
            this.mCrimes = crimes;
        }
    }
}
