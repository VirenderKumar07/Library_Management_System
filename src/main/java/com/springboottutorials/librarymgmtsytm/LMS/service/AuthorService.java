package com.springboottutorials.librarymgmtsytm.LMS.service;

import com.springboottutorials.librarymgmtsytm.LMS.dto.AuthorDto;
import com.springboottutorials.librarymgmtsytm.LMS.entity.AuthorEntity;
import com.springboottutorials.librarymgmtsytm.LMS.exception.ResourceNotFoundException;
import com.springboottutorials.librarymgmtsytm.LMS.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorDto createNewAuthor(AuthorDto inputAuthor) {
       AuthorEntity authorEntity = modelMapper.map(inputAuthor, AuthorEntity.class);
       AuthorEntity toSavedAuthor = authorRepository.save(authorEntity);
       return  modelMapper.map(toSavedAuthor, AuthorDto.class);

}

    public List<AuthorDto> getAllTheAuthor() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities
                .stream()
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDto.class))
                .collect(Collectors.toList());
    }

    public Optional<AuthorDto> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDto.class));
    }


    public void isExistAuthorById(Long authorid){
        boolean exist = authorRepository.existsById(authorid);
        if (!exist)
            throw new ResourceNotFoundException("Author not found with the id:"+authorid);
    }

    public boolean deleteTheAuthor(Long authorId) {
        isExistAuthorById(authorId);
        authorRepository.deleteById(authorId);
        return true;
    }

    public List<AuthorDto> getAuthorByName() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities
                .stream()
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDto.class))
                .collect(Collectors.toList());



    }
}
