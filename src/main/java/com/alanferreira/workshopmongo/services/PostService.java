package com.alanferreira.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanferreira.workshopmongo.domain.Post;
import com.alanferreira.workshopmongo.repository.PostRepository;
import com.alanferreira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
		
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		/**return repo.findByTitleContainingIgnoreCase(text);**/
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch (String text, Date min, Date max){
		max = new Date(max.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, min, max);
	}
}
	

