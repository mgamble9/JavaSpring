package com.gamble.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findByArtistContaining(String search);
//    Long countByTitleContaining(String search);
//    Long deleteByTitleStartingWith(String search);
    List<Song> findTop10ByOrderByRatingDesc();

}
