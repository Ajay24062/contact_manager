package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.entity.Contact;
import com.example.smartcontactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contacts/list";
    }

    @GetMapping("/new")
    public String showNewContactForm(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contacts/new";
    }

    @PostMapping
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        contactService.addContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        model.addAttribute("contact", contact);
        return "contacts/edit";
    }

    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute("contact") Contact contact) {
        contactService.updateContact(id, contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }
}
