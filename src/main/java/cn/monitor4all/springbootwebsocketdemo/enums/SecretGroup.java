package cn.monitor4all.springbootwebsocketdemo.enums;

import java.util.*;

public enum SecretGroup {

    NIUZIJIAN("FlyNiu"),

    GENGMINGHUI("Gintama"),

    ZHANGLUOTONG("TTTTTT"),
    ;

    public String getName() {
        return name;
    }

    private String name;
    SecretGroup (String name) {
        this.name = name;
    }

    public static Set<String> getAllNames() {
        Set<String> result = new HashSet<>();
        SecretGroup[] enums = SecretGroup.values();
        for (SecretGroup anEnum : enums) {
            result.add(anEnum.getName());
        }
        return result;
    }
}
