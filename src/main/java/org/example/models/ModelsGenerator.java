package org.example.models;

import com.github.javafaker.Faker;

public class ModelsGenerator {

    public static PostRequest generatePostModelWithValidData() {
        Faker faker = new Faker();
        return PostRequest.builder().userId(faker.number().numberBetween(1, 10))
                .title(faker.lorem().characters(10, 20))
                .body(faker.lorem().characters(20, 100))
                .build();
    }
}
