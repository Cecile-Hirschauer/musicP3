package com.example.musicp3_springboot.repository;

import com.example.musicp3_springboot.entity.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepositoryInterface extends CrudRepository<Music, String> {
}
