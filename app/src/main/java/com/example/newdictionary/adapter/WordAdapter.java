package com.example.newdictionary.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdictionary.R;
import com.example.newdictionary.diffutil.MyDiffUtilCallBack;
import com.example.newdictionary.model.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> mWords;
    private Context mContext;

    public WordAdapter(List<Word> words, Context context) {
        mWords = words;
        mContext = context;
    }

    public List<Word> getWords() {
        return mWords;
    }

    public void setWords(List<Word> words) {
        mWords = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.row_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.bindWord(mWords.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet()) {
                holder.bindWord((Word) bundle.getSerializable(MyDiffUtilCallBack.BUNDLE_WORD));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void updateWords(List<Word> newWords) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new MyDiffUtilCallBack(mWords, newWords));
        diffResult.dispatchUpdatesTo(this);
        mWords.clear();
        mWords.addAll(newWords);
    }


    public class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewWord = itemView.findViewById(R.id.txt_word_name);
        }

        public void bindWord(Word word) {
            mTextViewWord.setText(word.getName());
        }
    }
}
