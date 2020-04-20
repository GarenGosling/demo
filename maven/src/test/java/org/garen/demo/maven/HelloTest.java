package org.garen.demo.maven;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/4/20 下午6:02
 */
@Slf4j
public class HelloTest {

    @Test
    public void testHello() {
        Hello hello = new Hello();
        log.info(hello.Hello());
    }

}
