package com.movies.demo;

import com.movies.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private JdbcClient jdbcClient;

    @GetMapping("/{director}")
    public List<Movie> findByDirector(@PathVariable String director)
    {
        return jdbcClient.sql("SELECT id , name , director from movie where director =:director " )
                .param("director" , director)
                .query((rs, rowNum) -> new Movie(rs.getInt("id"), rs.getString("name"), rs.getString("director")))
                .list();
    }

    @GetMapping
    public List<Movie> findAll()
    {
        return jdbcClient.sql("SELECT id , name , director from movie" )
                .query(Movie.class)
                .list();
    }

    @PostMapping
    public String addMovie(@RequestBody Movie movie)
    {
        jdbcClient.sql("INSERT INTO MOVIE(id, name , director)  VALUES(?,?,?)")
                .param(1, movie.getId())
                .param(2, movie.getName())
                .param(3, movie.getDirector())
                .update();

        return "Your Movie has been Added";
    }



}
