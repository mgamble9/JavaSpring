package com.gamble.group_languages.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.group_languages.models.Languages;

@Service
public class LanguageService {
	
    private List<Languages> languages = new ArrayList(Arrays.asList(
            new Languages("Java", "James Gosling", "1.8"),
            new Languages("Python", "Guido van Rossum", "3.6"),
            new Languages("Javascript", "Brendan Eich", "1.9")
            ));
    
    // returns all the books
    public List<Languages> allLanguages() {
        return languages;
    }

    public void addLanguage(Languages language) {
        languages.add(language);
    }

    public Languages findLanguageByIndex(int index) {
        if (index < languages.size()){
            return languages.get(index);
        }else{
            return null;
        }
    }

    public void destroyLanguage(int id) {
        if (id < languages.size()){
            languages.remove(id);
        }
    }

    public void updateLanguage(int id, Languages language) {
        if (id < languages.size()){
            languages.set(id, language);
        }
    }

}
