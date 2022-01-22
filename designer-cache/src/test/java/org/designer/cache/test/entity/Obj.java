package org.designer.cache.test.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: Designer
 * @date : 2021/9/18 21:35
 */
@Data
@ToString
public class Obj implements Serializable {
    private static final long serialVersionUID = -364617991898385429L;
    private Long id;
    private Long time;
    private LocalDateTime datetime;
    private String str;

}
