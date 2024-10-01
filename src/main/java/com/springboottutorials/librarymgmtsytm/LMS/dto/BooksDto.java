package com.springboottutorials.librarymgmtsytm.LMS.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {

    private Long id;

    private String title;
}
