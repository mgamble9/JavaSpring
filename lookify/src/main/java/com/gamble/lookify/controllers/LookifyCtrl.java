package com.gamble.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.lookify.models.Song;
import com.gamble.lookify.services.SongService;

@Controller
public class LookifyCtrl {

	private final SongService songService;

    public LookifyCtrl(SongService songService){
        this.songService = songService;
    }

    @RequestMapping("/")
    public String home() {
        return "/WEB-INF/views/index.jsp";
    }

	  @RequestMapping("/dashboard")
	  public String dashboard(Model model,
	  							@ModelAttribute("song") Song song) {
	      List<Song> song_list = songService.allSongs();
	      model.addAttribute("songs", song_list);
	      return "/WEB-INF/views/dashboard.jsp";
	  }
//  @RequestMapping("/dashboard")
//  public String dashboard(Model model,
//  							@ModelAttribute("search") String search) {
//      List<Song> song_list = songService.allSongs();
//      model.addAttribute("songs", song_list);
//      return "/WEB-INF/views/dashboard.jsp";
//  }
    
	@PostMapping("/dashboard")
		public String searchRoute(@ModelAttribute("song") Song song) {
		         return "redirect:/search/" + song.getArtist();        
	}
//	@PostMapping("/dashboard")
//		public String searchRoute(@ModelAttribute("string") String string) {
//		         return "redirect:/search/" + string;
//	}

    @RequestMapping("/search/{search_str}")
    	public String searchPage(@PathVariable("search_str") String search_str,
    							Model model) {
    		List<Song> song_list = songService.searchSongs(search_str);
        model.addAttribute("songs", song_list);
        return "/WEB-INF/views/searchPage.jsp";

    }

    @RequestMapping("/songs/topten")
    public String languages(Model model) {
    		List<Song> top_ten_song_list = songService.toptenSongs();
    		model.addAttribute("songs", top_ten_song_list);
        return "/WEB-INF/views/topten.jsp";
    }
    
    @RequestMapping("/songs/new")
    public String languages(@ModelAttribute("song") Song song) {
        return "/WEB-INF/views/addsong.jsp";
    }

    @PostMapping("/songs/new")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/addsong.jsp";
        }else{
            songService.addSong(song);
            return "redirect:/songs/new";
        }
    }

    @RequestMapping("/songs/{id}")
    public String languages(@PathVariable("id") Long id,
    							Model model) {
    		Song song = songService.findSongById(id);
        if (song != null){
            model.addAttribute("song", song);
            return "/WEB-INF/views/displaySong.jsp";
        }else{
            return "redirect:/dashboard";
        }
    }
    
    @RequestMapping(value="/songs/delete/{id}")
    public String destroySong(@PathVariable("id") Long id) {
        songService.destroySong(id);
        return "redirect:/dashboard";
    }

    

}
