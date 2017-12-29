package com.pecuyu.litemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pecuyu.litemusic.R;
import com.pecuyu.litemusic.views.CollapseItemViewContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment implements View.OnClickListener, CollapseItemViewContainer.ContainerCollapseChangeListener {


    private CollapseItemViewContainer musicColumn;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        musicColumn = (CollapseItemViewContainer) view.findViewById(R.id.id_music_column);
//        TextView child = new TextView(getActivity());
//        child.setText("test");
//        musicColumn.addView(child);
//
//        child = new TextView(getActivity());
//        child.setText("test2");
//        musicColumn.addView(child);
//
//        child = new TextView(getActivity());
//        child.setText("test3");
//        musicColumn.addView(child);
        musicColumn.setOnClickListener(this);
        musicColumn.setOnContainerCollapseChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
        if (v == musicColumn) {
            musicColumn.switchCollapseState();
        }
    }

    @Override
    public void onContainerCollapse(View v) {

    }

    @Override
    public void onContainerShow(View v) {

    }
}
