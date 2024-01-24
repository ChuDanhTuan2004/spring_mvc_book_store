package org.example.book_store.controller;

import net.bytebuddy.implementation.bytecode.ShiftRight;
import org.example.book_store.model.Book;
import org.example.book_store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public String showList(Model model, @PageableDefault(value = 1) Pageable pageable) {
        model.addAttribute("bookList", bookRepository.findAll(pageable));
        return "/list";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "/create";
    }

    @PostMapping("/create")
    public String create(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("errorList", bindingResult.getAllErrors());
            return "/create";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("errorList", bindingResult.getAllErrors());
            return "/edit";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model,@PageableDefault(value = 1) Pageable pageable){
        model.addAttribute("bookList", bookRepository.findAllByNameContaining(name, pageable));
        return "/result";
    }
}
