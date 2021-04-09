package cn.graydove.server.repository;

import cn.graydove.server.model.document.ChapterDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author graydove
 */
@Repository
public interface ChapterDetailRepository extends MongoRepository<ChapterDetail, ObjectId> {

    List<ChapterDetail> findByBookId(Long bookId);

    Optional<ChapterDetail> findByChapterId(Long chapterId);
}
