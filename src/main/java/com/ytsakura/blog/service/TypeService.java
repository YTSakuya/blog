package com.ytsakura.blog.service;

import com.ytsakura.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/30 2:41
 * @describe
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long type);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
