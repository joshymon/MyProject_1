package com.example.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class IMKafkaUtils {
	public static final String KAFKA_BROKER = "localhost:9092";
    public static final String GROUP_ID = "kafka-sandbox";
	private AdminClient client;
	private KafkaTemplate<String, String> template;
	
	public IMKafkaUtils(KafkaTemplate<String, String> template) {
		this.client = getAdminClient();
		this.template = template;
	}

	public AdminClient getAdminClient() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        AdminClient client = AdminClient.create(config);
        return client;
	 }
	
	public void createTopics(String consumerTopic) {
		List<NewTopic> topics = new ArrayList<>();
        Map<String, String> topicConfigs = new HashMap<>();
        topicConfigs.put("retention.ms", Long.toString(Long.MAX_VALUE));

        NewTopic ratings = new NewTopic(consumerTopic, Integer.parseInt("1"), Short.parseShort("1"));
        ratings.configs(topicConfigs);
        topics.add(ratings);

        NewTopic counts = new NewTopic(consumerTopic +"-counts", Integer.parseInt("1"), Short.parseShort("1"));
        counts.configs(topicConfigs);
        topics.add(counts);

        client.createTopics(topics);
	 }
	
	public List<String> getTopicsList() throws InterruptedException, ExecutionException {
		List<String> topics = new ArrayList<>();
		ListTopicsResult ls = client.listTopics();
		Set<String> s = ls.names().get();
		topics.addAll(s);
		return topics;
	}
	
	public void sendMessage(String consumer, String mStr) {
		template.send(consumer, mStr);
	}
	 
	 
}
