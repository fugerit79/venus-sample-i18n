// generated from template 'DocHelperTest.ftl' on 2025-10-07T09:42:47.836+02:00
package test.org.fugerit.java.demo.venussamplei18n;

import org.fugerit.java.demo.venussamplei18n.DocHelper;
import org.fugerit.java.doc.base.config.DocConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;
/**
 * This is a basic example of Fugerit Venus Doc usage,
 * running this junit will :
 * - creates data to be used in document model
 * - renders the 'document.ftl' template
 * - print the result in markdown format
 *
 * For further documentation :
 * https://github.com/fugerit-org/fj-doc
 *
 * NOTE: This is a 'Hello World' style example, adapt it to your scenario, especially :
 *  - change the doc handler and the output mode (here a ByteArrayOutputStream buffer is used)
 */
@Slf4j
class DocHelperTest {

    @Test
    void testDocProcess() throws Exception {
        DocHelper docHelper = new DocHelper();
        for (  Locale language : Arrays.asList(Locale.ENGLISH, Locale.ITALIAN) ) {
            // handler id
            String handlerId = DocConfig.TYPE_MD;
            log.info( "generating {} document for language {}", handlerId, language );
            // create custom data for the fremarker template 'document.ftl'
            try ( ByteArrayOutputStream baos = new ByteArrayOutputStream() ) {
                // output generation
                docHelper.generateDocument( handlerId, language, baos );
                // print the output
                log.info( "{} output : \n{}", handlerId, new String( baos.toByteArray(), StandardCharsets.UTF_8 ) );
                Assertions.assertNotEquals( 0, baos.size() );
            }
        }
    }

}
