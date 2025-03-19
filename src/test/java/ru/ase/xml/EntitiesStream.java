package ru.ase.xml;

import ru.ase.entity.DesignCondition;
import ru.ase.entity.Report;
import ru.ase.entity.analysis.ABDImpactAnalysis;
import ru.ase.entity.analysis.ImpactAnalysis;
import ru.ase.entity.analysis.attribute.ChangeRequest;
import ru.ase.entity.analysis.attribute.Criticality;
import ru.ase.entity.analysis.attribute.Stage;
import ru.ase.entity.analysis.attribute.Status;
import ru.ase.entity.approximation.Approximation;
import ru.ase.entity.document.Document;
import ru.ase.entity.document.DocumentType;
import ru.ase.entity.event.InitialEvent;
import ru.ase.entity.event.InitialEventGroup;
import ru.ase.entity.plug.File;
import ru.ase.entity.plug.Limitation;
import ru.ase.entity.plug.Requirement;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.structure.unit.Building;
import ru.ase.entity.tag.*;
import ru.ase.entity.task.Task;

import java.util.stream.Stream;

public class EntitiesStream {
    static public Stream<Class<?>> externalEntityClasses() {
        return Stream.of(
                InitialEvent.class,
                InitialEventGroup.class,
                Tag.class,
                TagAttribute.class,
                TagAttributeValue.class,
                Document.class,
                DocumentType.class,
                System.class,
                Building.class,
                PBSCode.class,
                DesignCondition.class,
                ImpactAnalysis.class
        );
    }

    static public Stream<Class<?>> allEntityClasses() {
        return Stream.concat(
                externalEntityClasses(),
                Stream.of(
                        ChangeRequest.class,
                        Criticality.class,
                        Stage.class,
                        Status.class,
                        ABDImpactAnalysis.class,
                        Approximation.class,
                        File.class,
                        Limitation.class,
                        Requirement.class,
                        Task.class,
                        Report.class
                ));
    }
}
