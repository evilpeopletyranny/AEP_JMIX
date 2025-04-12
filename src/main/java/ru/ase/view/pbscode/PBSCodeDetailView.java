package ru.ase.view.pbscode;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import ru.ase.entity.structure.PBSCode;
import ru.ase.view.main.MainView;

@Route(value = "pBSCodes/:id", layout = MainView.class)
@ViewController(id = "PBSCode.detail")
@ViewDescriptor(path = "pbs-code-detail-view.xml")
@EditedEntityContainer("pBSCodeDc")
public class PBSCodeDetailView extends StandardDetailView<PBSCode> {
}