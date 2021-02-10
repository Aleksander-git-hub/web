package com.project.web.service;

import com.project.web.dto.AuthorDto;
import com.project.web.entity.AuthorEntity;
import com.project.web.exceptions.NotFoundException;
import com.project.web.mapper.AuthorMapper;
import com.project.web.repository.AuthorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

}
