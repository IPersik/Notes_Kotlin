package com.example.noteskotlin.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteskotlin.R;
import com.example.noteskotlin.data.NoteData;
import com.example.noteskotlin.data.NoteSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    private final static String TAG = "NoteAdapter";
    private NoteSource noteSource;
    private final Fragment fragment;
    private AdapterView.OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне
    private int menuPosition;
    private NoteSource dataSource;

    public NoteAdapter(NoteSource noteSource, Fragment fragment){
        this.noteSource = dataSource;
        this.fragment = fragment;
    }

    public NoteAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setData(dataSource.getNoteDate(position));
    }

    public void setDataSource(NoteSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    //new
    public void SetOnItemClickListener(AdapterView.OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    //

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = ((LinearLayout) itemView).findViewById(R.id.textView);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT);
                }
            };
        }

        private void setData(NoteData noteData){
            title.setText(noteData.getTitle());
        }
    }
}

