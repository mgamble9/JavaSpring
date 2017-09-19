package com.gamble.lookify.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.lookify.models.Song;
import com.gamble.lookify.repositories.SongRepository;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }
    
    public List<Song> allSongs() {
		return (List<Song>) songRepository.findAll();
    }

    public List<Song> searchSongs(String search) {
		return (List<Song>) songRepository.findByArtistContaining(search);
    }

    public List<Song> toptenSongs() {
		return (List<Song>) songRepository.findTop10ByOrderByRatingDesc();
    }

	public void addSong(Song song) {
			songRepository.save(song);
	}
	
    public Song findSongById(Long id) {
        return songRepository.findOne(id);
    }

    public void destroySong(Long id) {
     		songRepository.delete(id);
    }

    public void updateSong(Song song) {
     		songRepository.save(song);
    }
    
}
