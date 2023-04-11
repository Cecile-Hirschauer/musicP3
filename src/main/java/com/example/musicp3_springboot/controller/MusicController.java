package com.example.musicp3_springboot.controller;

import com.example.musicp3_springboot.entity.Music;
import com.example.musicp3_springboot.form.CreateMusicForm;
import com.example.musicp3_springboot.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;
    @RequestMapping("/")
    public String displayHome(){
        return "index";
    }

    @RequestMapping("/list")
    public @ModelAttribute("musics") List<Music> displaylist() {
        List<Music> musics = musicRepository.list();
        return musics;
    }

    @GetMapping("/view-music/"+"{id}")
    public  String displayVue(@PathVariable("id") long number, Model model, RedirectAttributes redirectAttributes) {
        Music music = musicRepository.getId(number);
        model.addAttribute("music", music);
        return "view-music";
    }

    @GetMapping("/create-music")
    public String displayAdd(@ModelAttribute CreateMusicForm createMusicForm) {
        return "create-music";
    }

    @PostMapping("/createMusic")
    public String createMusic(@ModelAttribute CreateMusicForm createMusicForm) {
        System.out.println("je suis dans le postMapping");
        Music music = new Music();
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.add(music);
        return "successForm";

    }





}
