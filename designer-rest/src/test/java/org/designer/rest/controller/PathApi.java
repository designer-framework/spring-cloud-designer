package org.designer.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/26 14:21
 */
@Slf4j
@RestController
public class PathApi {

    @RequestMapping(value = "/path", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void http2(@RequestParam Map<String, Object> servletRequest) {
        System.out.println(servletRequest);
    }

}
