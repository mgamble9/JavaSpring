package com.gamble.dojo_overflow.services;

import org.springframework.stereotype.Service;

import com.gamble.dojo_overflow.models.Tag;
import com.gamble.dojo_overflow.repositories.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		super();
		this.tagRepository = tagRepository;
	}
	
	public void addTag(Tag tag) {
		tagRepository.save(tag);
	}
	
	public void updateTag(Tag tag) {
		tagRepository.save(tag);
	}
	
	public Tag findTagBySubject(String subject) {
		return tagRepository.findTagBySubject(subject);
	}
}
