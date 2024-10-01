package com.springboottutorials.librarymgmtsytm.LMS.controller;


import com.springboottutorials.librarymgmtsytm.LMS.dto.AuthorDto;
import com.springboottutorials.librarymgmtsytm.LMS.exception.ResourceNotFoundException;
import com.springboottutorials.librarymgmtsytm.LMS.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllTheAuthor(){
        return ResponseEntity.ok(authorService.getAllTheAuthor());
    }

    @GetMapping(path = "/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable (name = "authorId") Long authorId){
        Optional<AuthorDto> authorDto = authorService.getAuthorById(authorId);
        return authorDto
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found with id: "+authorId));
    }

    @GetMapping(path = "/by-name")
    public ResponseEntity<List<AuthorDto>> getAuthorByName(@RequestParam(required = false, name = "name")String name,
                                        @RequestParam(required = false)String sortBy){
        return ResponseEntity.ok(authorService.getAuthorByName());
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody AuthorDto inputAuthor){
        AuthorDto savedAuthor = authorService.createNewAuthor(inputAuthor);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{authorId}")
    public ResponseEntity<Boolean> deleteTheAuthor(@PathVariable Long authorId){
        boolean gotDeleted = authorService.deleteTheAuthor(authorId);
        if (gotDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
