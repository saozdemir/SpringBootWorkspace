package com.sao.threadcourse.sec01;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 17 Ara 2025
 * <p>
 * @description:
 */
public enum Enu {
    STARTED(1),
    IN_PROGRESS(2),
    COMPLETED(3);

    private final int code;

    Enu(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Enu fromCode(int code) {
        for (Enu e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
