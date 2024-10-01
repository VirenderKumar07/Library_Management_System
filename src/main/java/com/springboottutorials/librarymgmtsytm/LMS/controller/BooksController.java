package com.springboottutorials.librarymgmtsytm.LMS.controller;


import com.springboottutorials.librarymgmtsytm.LMS.dto.BooksDto;
import com.springboottutorials.librarymgmtsytm.LMS.entity.BooksEntity;
import com.springboottutorials.librarymgmtsytm.LMS.exception.ResourceNotFoundException;
import com.springboottutorials.librarymgmtsytm.LMS.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/books")
public class BooksController {

    private final BooksService booksService;

    @GetMapping()
    public ResponseEntity<List<BooksDto>> getAllTheBooks(){
        return ResponseEntity.ok(booksService.getAllTheBooks());

    }

    @GetMapping(path = "/{booksId}")
    public ResponseEntity<BooksDto> getBooksById(@PathVariable Long booksId){
        Optional<BooksDto> booksDto = booksService.getBooksById(booksId);
        return booksDto
                .map(ResponseEntity::ok)
                .orElseThrow(() ->new ResourceNotFoundException("Books not found with the id is :"+booksId));
    }

    @GetMapping(path = "/by-title")
    public ResponseEntity<List<BooksDto>> getBooksByTitle(@RequestParam(required = false, name = "title") String title,
                                       @RequestParam(required = false) String sortBy)
    {
        return ResponseEntity.ok(booksService.getBooksByTitle());
    }

    @PostMapping
    public ResponseEntity<BooksDto> createNewBooks(@RequestBody BooksDto inputbooks){
        BooksDto savedbooks = booksService.createNewBooks(inputbooks);
        return new ResponseEntity<>(savedbooks, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{booksId}/author/{authorId}")
    public BooksEntity assignTheBookToTheAuthor(@PathVariable Long authorId,
                                                @PathVariable Long booksId){
        return booksService.assignTheBookToTheAuthor( authorId, booksId);
    }

    @DeleteMapping(path = "/{booksId}")
    public ResponseEntity<Boolean> deleteTheBook(@PathVariable Long booksId){
         boolean gotDeleted = booksService.deleteTheBook(booksId);
         if(!gotDeleted)
             return ResponseEntity.ok(true);
         return ResponseEntity.notFound().build();
    }

}
