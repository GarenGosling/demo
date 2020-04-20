package org.garen.demo.maven;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/4/20 下午5:59
 */
@Slf4j
public class Hello {

    public String Hello() {
        return "Hello maven!";
    }

    public static void main(String[] args) {
        log.info(new Hello().Hello());
    }
}
