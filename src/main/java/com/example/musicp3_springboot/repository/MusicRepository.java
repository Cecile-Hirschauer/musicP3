package com.example.musicp3_springboot.repository;

import com.example.musicp3_springboot.entity.Music;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MusicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Music> list(){
        return jdbcTemplate.query("SELECT MUSIC_ID, TITLE, DESCRIPTION FROM MUSIC",
                (rs, rowNum) -> new Music(
                        String.valueOf(rs.getLong("MUSIC_ID")),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION")
                ));
    }

    public void add (@NotNull Music music) {
        jdbcTemplate.update("INSERT INTO MUSIC ( title, description) VALUES(?,?)",
                music.getTitle(), music.getDescription());
    };

    public Music getId(Long id) {
        String sql = "SELECT * FROM MUSIC WHERE MUSIC_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Music(
                        String.valueOf(rs.getLong("MUSIC_ID")),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION")
                ));
    }


}