package ru.ase.entity.tag.attibute;


import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TagAttributeFactory {
    private final DataManager dataManager;

    public TagAttributeFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public TagAttribute create(String name) {
        TagAttribute tagAttribute = dataManager.create(TagAttribute.class);
        tagAttribute.setId(UUID.randomUUID());
        tagAttribute.setAttribute(name);
        return tagAttribute;
    }
}
