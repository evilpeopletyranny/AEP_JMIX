package ru.ase.view.building;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import ru.ase.entity.structure.unit.building.Building;
import ru.ase.view.main.MainView;

@Route(value = "buildings/:id", layout = MainView.class)
@ViewController(id = "Building.detail")
@ViewDescriptor(path = "building-detail-view.xml")
@EditedEntityContainer("buildingDc")
public class BuildingDetailView extends StandardDetailView<Building> {
}