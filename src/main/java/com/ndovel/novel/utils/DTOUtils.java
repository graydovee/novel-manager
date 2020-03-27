package com.ndovel.novel.utils;

import com.ndovel.novel.model.dto.base.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public final class DTOUtils {
    private DTOUtils(){

    }

    public static<DTO extends BaseDTO<DTO, DOMAIN>, DOMAIN> List<DTO> listToDTOs(List<DOMAIN> list, Class<DTO> clazz){
        List<DTO> l = new ArrayList<>();
        list.forEach(domain -> {
            DTO dto = ReflectUtils.newBean(clazz);
            l.add(dto.init(domain));
        });
        return l;
    }

}
