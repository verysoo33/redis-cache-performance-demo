package soo.demo.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class CustomDialectConfig extends MySQL8Dialect {

    public CustomDialectConfig() {
        super();

        // AES_DECRYPT 함수 등록
        registerFunction("aes_decrypt", new StandardSQLFunction("AES_DECRYPT", new StringType()));
        registerFunction("unhex", new StandardSQLFunction("UNHEX", new StringType()));
    }
}