package com.chan.hystrix.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chan.hystrix.service.TagService;

/**
 * @author chanyun
 */
@Service
public class TagServiceImpl implements TagService {

    public List<String> queryTag() {
        return Arrays.asList("Tag");
    }
}
