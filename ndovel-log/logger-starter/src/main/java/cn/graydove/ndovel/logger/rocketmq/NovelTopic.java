package cn.graydove.ndovel.logger.rocketmq;

/**
 * @author graydove
 */
public interface NovelTopic {

    String NOVEL_TOPIC = "novel";

    String VISIT_TOPIC = NOVEL_TOPIC + "_" + "visit";

    String VISIT_STATISTIC_TOPIC = NOVEL_TOPIC + "_" + "visit_statistic";
}
