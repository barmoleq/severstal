package com.ithub.zapisaca.controllers;

import com.ithub.zapisaca.models.Notepad;
import com.ithub.zapisaca.repo.NotepadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NotepadController {

    @Autowired
    private NotepadRepository notepadRepository;
    private boolean check = true;


    @GetMapping("/notepad")
    public String notepadMain(Model model){

        //Создание записи при первом запуске

        if(notepadRepository.count() == 0 && check){
            notepadRepository.save(new Notepad("Hello world!", "Важное, Первая заметка", "Hello world!"));
            check=false;
        }
        Iterable<Notepad> notepads = notepadRepository.findAll();
        model.addAttribute("notepads", notepads);
        return "notepad-main";
    }

    @GetMapping("/notepad/add")
    public String notepadAdd(Model model){
        return "notepad-add";
    }

    @PostMapping("/notepad/add")
    public String notepadWriteAdd(@RequestParam String name, @RequestParam String tag, @RequestParam String text, Model model){
        Notepad notepad = new Notepad(name, tag, text);
        notepadRepository.save(notepad);
        return "redirect:/notepad";
    }

    @GetMapping("/notepad/{id}")
    public String notepadDetails(@PathVariable(value = "id") long id, Model model){
        if(!notepadRepository.existsById(id)){
            return "redirect:/notepad";
        }

        Optional<Notepad> notepad = notepadRepository.findById(id);
        ArrayList<Notepad> res = new ArrayList<>();
        notepad.ifPresent(res::add);
        model.addAttribute("notepad", res);
        return "notepad-details";
    }

    @GetMapping("/notepad/{id}/edit")
    public String notepadEdit(@PathVariable(value = "id") long id, Model model){
        if(!notepadRepository.existsById(id)){
            return "redirect:/notepad";
        }

        Optional<Notepad> notepad = notepadRepository.findById(id);
        ArrayList<Notepad> res = new ArrayList<>();
        notepad.ifPresent(res::add);
        model.addAttribute("notepad", res);
        return "notepad-edit";
    }

    @PostMapping("/notepad/{id}/edit")
    public String notepadWriteUpdate(@PathVariable(value = "id") long id, @RequestParam String name,  @RequestParam String tag, @RequestParam String text, Model model){
        Notepad notepad = notepadRepository.findById(id).orElseThrow();
        notepad.setName(name);
        notepad.setTag(tag);
        notepad.setText(text);
        notepadRepository.save(notepad);
        return "redirect:/notepad";
    }

    @PostMapping("/notepad/{id}/remove")
    public String notepadWriteDelete(@PathVariable(value = "id") long id, Model model){
        Notepad notepad = notepadRepository.findById(id).orElseThrow();
        notepadRepository.delete(notepad);
        return "redirect:/notepad";
    }
}
