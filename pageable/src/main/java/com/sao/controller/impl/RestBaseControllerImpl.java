package com.sao.controller.impl;

import com.sao.controller.IRestBaseController;
import com.sao.utils.PagerUtil;
import com.sao.utils.RestPageableEntity;
import com.sao.utils.RestPageableRequest;
import com.sao.utils.RestRootEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description:
 */
public class RestBaseControllerImpl implements IRestBaseController {
    @Override
    public Pageable toPageable(RestPageableRequest request) {
        return PagerUtil.toPageable(request);
    }

    public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> content) {
        return PagerUtil.toPageableResponse((Page<T>) page, content);
    }

    public <T> RestRootEntity<T> ok(T payload) {
        return RestRootEntity.ok(payload);
    }
}
