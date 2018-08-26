package tpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-26 15:09
 **/
public class DigestTest {
    @Test
    public void sha1() {
        String password = "123456789";
        String encode = DigestUtils.sha1Hex(password.getBytes());
        //f7c3bc1d808e04732adf679965ccc34ca7ae3441|length|40
        System.out.println(encode + "|length|" + encode.length());
    }
}