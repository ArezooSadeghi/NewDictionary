package com.example.newdictionary.repository;

import com.example.newdictionary.model.Word;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Word> getWords();

    Word getWord(UUID wordId);

    void insertWord(Word word);

    void updateWord(Word word);

    void deleteWord(Word word);

    List<Word> searchResult(String query);

}
