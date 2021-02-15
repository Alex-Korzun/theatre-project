package com.dev.cinema.controller;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.request.MovieRequestDto;
import com.dev.cinema.model.dto.response.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.fromDto(movieRequestDto);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }
}