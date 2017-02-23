package com.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumRepository repo;

    public AlbumController(AlbumRepository repo) {
        this.repo = repo;
    }

    @PostMapping("")
    public Album postAlbum(@RequestBody Album album) {
        this.repo.save(album);
        return album;
    }

    @GetMapping("")
    public Iterable<Album> getAlbums() {
        return this.repo.findAll();
    }
}
