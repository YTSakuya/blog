package com.ytsakura.blog.service;

import com.ytsakura.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/30 2:41
 * @describe
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long type);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
