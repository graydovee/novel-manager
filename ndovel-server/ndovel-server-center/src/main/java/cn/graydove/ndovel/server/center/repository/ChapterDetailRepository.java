package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.center.model.document.ChapterDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author graydove
 */
@Repository
public interface ChapterDetailRepository extends MongoRepository<ChapterDetail, ObjectId> {

    List<ChapterDetail> findByBookId(Long bookId);

    List<ChapterDetail> findByChapterIdIn(Collection<Long> chapterIds);

    Optional<ChapterDetail> findByChapterId(Long chapterId);
}
