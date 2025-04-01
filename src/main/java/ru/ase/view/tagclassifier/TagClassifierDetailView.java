package ru.ase.view.tagclassifier;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.view.main.MainView;

@Route(value = "tagClassifiers/:id", layout = MainView.class)
@ViewController(id = "TagClassifier.detail")
@ViewDescriptor(path = "tag-classifier-detail-view.xml")
@EditedEntityContainer("tagClassifierDc")
public class TagClassifierDetailView extends StandardDetailView<TagClassifier> {
}