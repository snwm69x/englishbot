package com.snwm.englishbot.repository;

import com.snwm.englishbot.entity.Word;
import com.snwm.englishbot.entity.enums.WordLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value = "select z.id, z.transcription, z.translation, z.word from (words as w " +
            "join user_words as uw on w.id=uw.word_id and uw.user_id = :userChatId) as z",
            nativeQuery = true)
    Optional<List<Word>> findWordsByUser(@Param("userChatId") Long userId);

    List<Word> findByWordLevel(WordLevel wordLevel);

    Optional<Word> findById(Long id);
}