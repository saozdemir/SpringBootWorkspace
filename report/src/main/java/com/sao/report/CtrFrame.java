package com.sao.report;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 12 Jun 2025
 * <p>
 * @description:
 */
public class CtrFrame {
    private CtrFrame() {
    }

    public static CtrFrame getInstance(){
        return new CtrFrame();
    }
}
