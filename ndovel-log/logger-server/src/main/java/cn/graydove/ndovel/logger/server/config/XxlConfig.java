package cn.graydove.ndovel.logger.server.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @author graydove
 */
@Configuration
@Slf4j
public class XxlConfig {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlProperties xxlProperties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        Optional.ofNullable(xxlProperties.getAdmin()).map(XxlProperties.Admin::getAddresses).ifPresent(xxlJobSpringExecutor::setAdminAddresses);
        Optional.ofNullable(xxlProperties.getAccessToken()).ifPresent(xxlJobSpringExecutor::setAccessToken);
        Optional.ofNullable(xxlProperties.getExecutor()).map(XxlProperties.Executor::getAppName).ifPresent(xxlJobSpringExecutor::setAppname);
        Optional.ofNullable(xxlProperties.getExecutor()).map(XxlProperties.Executor::getIp).ifPresent(xxlJobSpringExecutor::setIp);
        Optional.ofNullable(xxlProperties.getExecutor()).map(XxlProperties.Executor::getPort).ifPresent(xxlJobSpringExecutor::setPort);
        Optional.ofNullable(xxlProperties.getExecutor()).map(XxlProperties.Executor::getLogPath).ifPresent(xxlJobSpringExecutor::setLogPath);
        Optional.ofNullable(xxlProperties.getExecutor()).map(XxlProperties.Executor::getLogRetentionDays).ifPresent(xxlJobSpringExecutor::setLogRetentionDays);

        return xxlJobSpringExecutor;
    }



}