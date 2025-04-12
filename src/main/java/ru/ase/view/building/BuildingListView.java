package ru.ase.view.building;

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
import ru.ase.entity.structure.unit.building.Building;
import ru.ase.entity.structure.unit.building.BuildingFactory;
import ru.ase.view.main.MainView;
import ru.ase.xls.utils.XLSParseUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Route(value = "buildings", layout = MainView.class)
@ViewController(id = "Building.list")
@ViewDescriptor(path = "building-list-view.xml")
@LookupComponent("buildingsDataGrid")
@DialogMode(width = "64em")
public class BuildingListView extends StandardListView<Building> {
    @Autowired
    private BuildingFactory buildingFactory;
    @ViewComponent
    private CollectionLoader<Building> buildingsDl;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private DataManager dataManager;

    @Subscribe("importBuildingButton")
    public void onImportBuildingButtonFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {
        FileTemporaryStorageBuffer storageBuffer = getFileTemporaryStorageBuffer(event.getReceiver());
        UUID fileId = Objects.requireNonNull(storageBuffer.getFileData()).getFileInfo().getId();

        processXlsxFile(fileId);

        buildingsDl.load();
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

        String buildingHeader = "ЗданиеRM";

        Iterator<Row> rowIterator = sheet.rowIterator();
        Map<String, Integer> headerMap = XLSParseUtils.readHeaders(rowIterator.next().cellIterator());

        List<String> codeList = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String building = XLSParseUtils.getStringCellValue(row, headerMap.get(buildingHeader));

            if (!building.isEmpty()) codeList.add(building);
        }

        Set<Building> res = codeList.stream().map(pbsCode -> buildingFactory.create("", pbsCode)).collect(Collectors.toSet());
        System.out.println(res.size());

        dataManager.saveAll(
                res
        );
    }

}