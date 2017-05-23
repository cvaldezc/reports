package icrperusa.test;

import org.apache.commons.lang.SystemUtils;
import org.junit.Test;

public class GetOS {

    @Test
    public void getName() {
        System.out.println(SystemUtils.IS_OS_LINUX);
        System.out.println(SystemUtils.IS_OS_WINDOWS);
        System.out.println(System.getProperty("os.name"));
    }

}
