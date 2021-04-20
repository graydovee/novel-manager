package cn.graydove.ndovel.server.center.converter;

import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.graydove.ndovel.server.center.model.search.NovelDO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.util.Date;
import java.util.Optional;

/**
 * @author graydove
 */
public class NovelConverter {

    private static CopyOptions copyOptions = CopyOptions.create(null, true,
            "createTime", "updateTime");

    public static NovelDO toNovelDO(NovelPutRequest novelPutRequest) {
        return Optional.ofNullable(novelPutRequest).map(request -> {
            NovelDO novelDO = BeanUtil.toBean(request, NovelDO.class);
            novelDO.setCreateTime(Optional.ofNullable(request.getCreateTime()).map(Date::getTime).orElseGet(System::currentTimeMillis));
            novelDO.setUpdateTime(Optional.ofNullable(request.getUpdateTime()).map(Date::getTime).orElseGet(System::currentTimeMillis));
            return novelDO;
        }).orElse(null);
    }


    public static NovelVO toNovelVO(NovelDO novelDO) {
        return Optional.ofNullable(novelDO).map(novel -> {
            NovelVO novelVO = BeanUtil.toBean(novel, NovelVO.class, copyOptions);
            novelVO.setCreateTime(new Date(novel.getCreateTime()));
            novelVO.setUpdateTime(new Date(novel.getUpdateTime()));
            return novelVO;
        }).orElse(null);
    }
}
