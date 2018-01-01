package com.pecuyu.litemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pecuyu.litemusic.R;
import com.pecuyu.litemusic.views.CollapseItemView;
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        musicColumn = (CollapseItemViewContainer) view.findViewById(R.id.id_music_column);

        final CollapseItemView itemView = new CollapseItemView(getActivity());
        itemView.setItemIcon(R.mipmap.icon_cd).setItemName("我喜欢的音乐").setItemDetail("60首").setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "itemView onClick "+itemView.getItemName(), Toast.LENGTH_SHORT).show();
            }
        }).setOnMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "itemView more onClick", Toast.LENGTH_SHORT).show();
            }
        });
        musicColumn.addItemView(itemView);
        musicColumn.setOnCollapseChangeListener(this);
        musicColumn.setItemManagerClickListener(new ItemManagerClickListener());
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == musicColumn) {
            // Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
            // musicColumn.switchCollapseState();
        }
    }


    class ItemManagerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "manager onClick", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemViewCollapse(ViewGroup parent) {
        Toast.makeText(getActivity(), "onItemViewCollapse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemViewShow(ViewGroup parent) {
        Toast.makeText(getActivity(), "onItemViewShow", Toast.LENGTH_SHORT).show();
    }
}
