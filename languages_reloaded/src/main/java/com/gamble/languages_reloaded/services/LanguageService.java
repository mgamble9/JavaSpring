package com.gamble.languages_reloaded.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.languages_reloaded.models.Languages;
import com.gamble.languages_reloaded.repositories.LanguageRepository;

@Service
public class LanguageService {
	
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }
	
//    private List<Languages> languages = new ArrayList(Arrays.asList(
//            new Languages("Java", "James Gosling", "1.8"),
//            new Languages("Python", "Guido van Rossum", "3.6"),
//            new Languages("Javascript", "Brendan Eich", "1.9")
//            ));
    
    // returns all the languages
    public List<Languages> allLanguages() {
    		return (List<Languages>) languageRepository.findAll();
       // return languages;
    }

    public void addLanguage(Languages language) {
//        languages.add(language);
    		languageRepository.save(language);
    }

//    public Languages findLanguageByIndex(int index) {
//        if (index < languages.size()){
//            return languages.get(index);
//        }else{
//            return null;
//        }
//    }

    public Languages findLanguageById(Long id) {
        return languageRepository.findOne(id);
    }

    public void destroyLanguage(Long id) {
        //if (id < languages.size()){
            //languages.remove(id);
    		//}
    		languageRepository.delete(id);
    }

    public void updateLanguage(Languages language) {
//        if (id < languages.size()){
//            languages.set(id, language);
//        }
    		languageRepository.save(language);
    }

}
