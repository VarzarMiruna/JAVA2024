package Home.Comands;

import Home.Repository.DocumentRepository;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportCommand implements Home.Comands.Command {
    private DocumentRepository repository;

    public ReportCommand(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: report <output_file_path>");
        }

        // Initialize Velocity
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        // Load the template
        Template template = velocityEngine.getTemplate("report_template.vm");

        // Prepare the context data
        VelocityContext context = new VelocityContext();
        context.put("documents", repository.getDocuments()); // Make sure getDocuments() returns a suitable list

        // Merge data with template
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Write the output to the specified file
        Files.write(Paths.get(args[1]), writer.toString().getBytes());
    }
}
