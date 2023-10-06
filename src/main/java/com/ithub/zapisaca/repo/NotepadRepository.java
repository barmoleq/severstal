package com.ithub.zapisaca.repo;

import com.ithub.zapisaca.models.Notepad;
import org.springframework.data.repository.CrudRepository;

public interface NotepadRepository extends CrudRepository<Notepad, Long> {
}
