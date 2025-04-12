package ru.ase.entity.tag.classifier;

import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TagClassifierFactory {
    private final DataManager dataManager;

    public TagClassifierFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public TagClassifier create(String name, TagClassifier parent) {
        TagClassifier tagClassifier = dataManager.create(TagClassifier.class);
        tagClassifier.setId(UUID.randomUUID());
        tagClassifier.setName(name);
        tagClassifier.setParentClassifier(parent);
        return tagClassifier;
    }
}