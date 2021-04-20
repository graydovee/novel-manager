package cn.graydove.ndovel.server.center.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author graydove
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${ndovel.elasticsearch.addr}")
    private String addr;

    @NotNull
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(addr)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
