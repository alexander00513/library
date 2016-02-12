package com.gmail.bogatyr.alexander.controller;

import com.gmail.bogatyr.alexander.entity.Catalog;
import com.gmail.bogatyr.alexander.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by alexander on 11.02.16.
 */
@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public String index() {
        return "This is a xml library, for update storage - please use /changeBook url part";
    }

    @RequestMapping(name = "/changeBook", method = PUT, consumes = APPLICATION_XML_VALUE, produces = TEXT_XML_VALUE)
    @ResponseBody
    public Catalog changeBook(@RequestBody Catalog catalog) {
        return nonNull(catalog) && !catalog.isEmpty() ? bookService.update(catalog) : bookService.findAll();
    }
}
