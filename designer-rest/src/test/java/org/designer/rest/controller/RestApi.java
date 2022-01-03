package org.designer.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/26 14:21
 */
@Slf4j
@RestController
public class RestApi {

    @RequestMapping(value = "/http20")
    public String http2(HttpServletRequest servletRequest) {
        log.info(servletRequest.getProtocol());
        return servletRequest.getProtocol();
    }

}
