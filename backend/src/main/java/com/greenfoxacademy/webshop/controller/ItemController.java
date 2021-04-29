package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.ItemDescriptionDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @CrossOrigin
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDescriptionDTO> getItemById(@PathVariable Long id)
            throws ItemNotFoundException {
        return ResponseEntity.ok(itemService
                .itemToDescriptionDTO(itemService.getItemById(id)));
    }

    @CrossOrigin
    @GetMapping("/item")
    public ResponseEntity<List<ItemResponseDTO>> getItemList(
            @RequestParam(required = false) String search,
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        return ResponseEntity.ok(
                itemService.getItems(search, page, pageSize)
        );
    }
}
