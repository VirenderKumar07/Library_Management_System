package com.springboottutorials.librarymgmtsytm.LMS.service;

import com.springboottutorials.librarymgmtsytm.LMS.dto.BooksDto;
import com.springboottutorials.librarymgmtsytm.LMS.entity.AuthorEntity;
import com.springboottutorials.librarymgmtsytm.LMS.entity.BooksEntity;
import com.springboottutorials.librarymgmtsytm.LMS.exception.ResourceNotFoundException;
import com.springboottutorials.librarymgmtsytm.LMS.repository.AuthorRepository;
import com.springboottutorials.librarymgmtsytm.LMS.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public List<BooksDto> getAllTheBooks() {
        List<BooksEntity> booksEntity = booksRepository.findAll();
        return booksEntity
                .stream()
                .map(booksEntity1 -> modelMapper.map(booksEntity1, BooksDto.class))
                .collect(Collectors.toList());
    }

    public BooksDto createNewBooks(BooksDto inputBooks) {
        BooksEntity booksEntity = modelMapper.map(inputBooks, BooksEntity.class);
        BooksEntity toSavedBooks = booksRepository.save(booksEntity);
        return modelMapper.map(toSavedBooks, BooksDto.class);
    }

    public Optional<BooksDto> getBooksById(Long booksId) {
        return booksRepository.findById(booksId)
                .map(booksEntity -> modelMapper.map(booksEntity, BooksDto.class));

    }


    public BooksEntity assignTheBookToTheAuthor(Long authorId, Long booksId) {
        Optional<BooksEntity> booksEntity = booksRepository.findById(booksId);
        Optional<AuthorEntity> authorEntity = authorRepository.findById(authorId);

        return booksEntity.flatMap(book ->
                authorEntity.map(author -> {
                            book.setAuthor(author);
                            return booksRepository.save(book);
                        })).orElse(null);
    }
    public void isExistTheBookById(Long bookId){
        boolean exist = booksRepository.existsById(bookId);
        if (!exist)
            throw new ResourceNotFoundException("Books not found with the id:"+bookId);
    }

    public Boolean deleteTheBook(Long booksId) {
        isExistTheBookById(booksId);
       booksRepository.deleteById(booksId);
       return true;

    }

    public List<BooksDto> getBooksByTitle() {
        List<BooksEntity> booksEntity = booksRepository.findAll();
        return booksRepository.findAll()
                .stream().map(booksEntity1 -> modelMapper.map(booksEntity1, BooksDto.class))
                .collect(Collectors.toList());
    }
}
