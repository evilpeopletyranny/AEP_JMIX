package ru.ase.view.tagclassifier;

import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.view.main.MainView;
import ru.ase.xls.TagClassifierXlsxImportService;

import java.io.File;
import java.util.Objects;
import java.util.UUID;


@Route(value = "tagClassifiers", layout = MainView.class)
@ViewController(id = "TagClassifier.list")
@ViewDescriptor(path = "tag-classifier-list-view.xml")
@LookupComponent("tagClassifiersDataGrid")
@DialogMode(width = "64em")
public class TagClassifierListView extends StandardListView<TagClassifier> {
    @Autowired
    private TagClassifierXlsxImportService tagClassifierXlsxImportService;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @ViewComponent
    private CollectionLoader<TagClassifier> tagClassifiersDl;

    @Subscribe("importFromFileButton")
    public void onImportFromFileButtonFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {
        FileTemporaryStorageBuffer storageBuffer = getFileTemporaryStorageBuffer(event.getReceiver());
        UUID fileId = Objects.requireNonNull(storageBuffer.getFileData()).getFileInfo().getId();

        processXlsxFile(fileId);

        tagClassifiersDl.load();
    }

    private FileTemporaryStorageBuffer getFileTemporaryStorageBuffer(Receiver receiver) {
        if (receiver instanceof FileTemporaryStorageBuffer storageBuffer) return storageBuffer;
        return null;
    }

    private void processXlsxFile(UUID fileId) {
        File file = temporaryStorage.getFile(fileId);
        if (file != null) {
            tagClassifierXlsxImportService.processFile(file);
            temporaryStorage.deleteFile(fileId);
        }
    }

}