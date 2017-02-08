package com.hazelcast.config;

import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.spi.serialization.SerializationService;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelTest.class})
public class WanConsumerConfigTest {

    private WanConsumerConfig config = new WanConsumerConfig();

    @Test
    public void testSerialization() {
        Map<String, Comparable> properties = new HashMap<String, Comparable>();
        properties.put("key", "value");

        config.setProperties(properties);
        config.setClassName("className");
        config.setImplementation("implementation");

        SerializationService serializationService = new DefaultSerializationServiceBuilder().build();
        Data serialized = serializationService.toData(config);
        WanConsumerConfig deserialized = serializationService.toObject(serialized);

        assertWanConsumerConfig(config, deserialized);
    }

    static void assertWanConsumerConfig(WanConsumerConfig expected, WanConsumerConfig actual) {
        if (expected == null) {
            return;
        }
        assertEquals(expected.getProperties(), actual.getProperties());
        assertEquals(expected.getClassName(), actual.getClassName());
        assertEquals(expected.getImplementation(), actual.getImplementation());
        assertEquals(expected.toString(), actual.toString());
    }
}