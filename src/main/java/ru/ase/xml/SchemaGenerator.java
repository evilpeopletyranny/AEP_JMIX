package ru.ase.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.SchemaOutputResolver;
import ru.ase.entity.tag.Tag;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class SchemaGenerator {
    private SchemaGenerator() {
    }

    public static void generateSchema(Class<?> clazz) throws JAXBException, IOException, jakarta.xml.bind.JAXBException {
        // Создаем JAXBContext для класса Order. Все связанные классы (Customer, Item)
        // будут автоматически обработаны.
        jakarta.xml.bind.JAXBContext context = JAXBContext.newInstance(Order.class);
        // Генерируем схему с использованием нашего SchemaOutputResolver.
        context.generateSchema(new InMemorySchemaResolver());
        System.out.println("XML-схема сгенерирована (см. файл order.xsd).");
    }
    // Реализация SchemaOutputResolver, которая сохраняет схему в файл.
    // При необходимости можно изменить реализацию, чтобы сохранить схему в памяти.
    static class InMemorySchemaResolver extends SchemaOutputResolver {
        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
            File file = new File("order.xsd");
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toString());
            return result;
        }
    }

    public static void main(String[] args) throws JAXBException, IOException, jakarta.xml.bind.JAXBException {
        SchemaGenerator.generateSchema(Order.class);
    }
}
