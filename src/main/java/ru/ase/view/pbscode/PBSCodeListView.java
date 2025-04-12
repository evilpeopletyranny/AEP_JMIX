package ru.ase.view.pbscode;

import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.structure.PbsCodeFactory;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.view.main.MainView;
import ru.ase.xls.tag.classifier.TagClassifierXlsxImportService;
import ru.ase.xls.utils.XLSParseUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Route(value = "pBSCodes", layout = MainView.class)
@ViewController(id = "PBSCode.list")
@ViewDescriptor(path = "pbs-code-list-view.xml")
@LookupComponent("pBSCodesDataGrid")
@DialogMode(width = "64em")
public class PBSCodeListView extends StandardListView<PBSCode> {
    @Autowired
    private PbsCodeFactory pbsCodeFactory;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @ViewComponent
    private CollectionLoader<PBSCode> pBSCodesDl;
    @Autowired
    private DataManager dataManager;

    @Subscribe("importPBSCodeButton")
    public void onImportPBSCodeButtonFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {
        FileTemporaryStorageBuffer storageBuffer = getFileTemporaryStorageBuffer(event.getReceiver());
        UUID fileId = Objects.requireNonNull(storageBuffer.getFileData()).getFileInfo().getId();

        processXlsxFile(fileId);

        pBSCodesDl.load();
    }

    private FileTemporaryStorageBuffer getFileTemporaryStorageBuffer(Receiver receiver) {
        if (receiver instanceof FileTemporaryStorageBuffer storageBuffer) return storageBuffer;
        return null;
    }

    private void processXlsxFile(UUID fileId) {
        File file = temporaryStorage.getFile(fileId);
        if (file != null) {
            try {
                processFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }
            temporaryStorage.deleteFile(fileId);
        }
    }

    private void processFile(File file) throws IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        String pbdCodeHeader = "PBS";
        String systemHeader = "Система";
        String buildingHeader = "ЗданиеRM";

        Iterator<Row> rowIterator = sheet.rowIterator();
        Map<String, Integer> headerMap = XLSParseUtils.readHeaders(rowIterator.next().cellIterator());

        List<String> codeList = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String pbsCode = XLSParseUtils.getStringCellValue(row, headerMap.get(pbdCodeHeader));
            String system = XLSParseUtils.getStringCellValue(row, headerMap.get(systemHeader));
            String building = XLSParseUtils.getStringCellValue(row, headerMap.get(buildingHeader));

            if (!pbsCode.isEmpty()) codeList.add(pbsCode);
            if (!system.isEmpty()) codeList.add(system);
            if (!building.isEmpty()) codeList.add(building);
        }

        Set<PBSCode> res = codeList.stream().map(pbsCodeFactory::create).collect(Collectors.toSet());
        System.out.println(res.size());

        dataManager.saveAll(
                res
        );
    }
}