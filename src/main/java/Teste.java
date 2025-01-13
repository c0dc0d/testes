import com.fasterxml.jackson.databind.JsonNode;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.util.TreeUtil;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.model.v3.OpenApi3;
import org.openapi4j.parser.model.v3.Schema;
import org.openapi4j.schema.validator.ValidationContext;
import org.openapi4j.schema.validator.ValidationData;
import org.openapi4j.schema.validator.v3.SchemaValidator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

public class Teste {


    public static void main(String[] args) throws Exception {
//        URL specURL = new URL("file:src/main/resources/spec.yaml");
        URL specURL = new URL("file:src/main/resources/spec-api-pagamentos.yaml");
        URL contentURL = new URL("file:src/main/resources/payload.json");
        String schemaName = "BrcbSaMaPatchPaymentRecordBody";

        OpenApi3 api = new OpenApi3Parser().parse(specURL, true);
        JsonNode contentNode = TreeUtil.load(contentURL);
        Schema schema = api.getComponents().getSchema(schemaName);
        JsonNode schemaNode = schema.toNode();

        SchemaValidator schemaValidator = new SchemaValidator(new ValidationContext<>(api.getContext()), null, schemaNode);

        ValidationData<Void> validation = new ValidationData<>();
        schemaValidator.validate(contentNode, validation);
        if (validation.isValid()) {
            System.out.println("ok");
        } else {
            System.out.println(validation.results());
        }

    }


}
