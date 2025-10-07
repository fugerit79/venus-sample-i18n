package org.fugerit.java.demo.venussamplei18n;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.doc.base.config.DocConfig;
import org.fugerit.java.doc.base.process.DocProcessContext;
import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfig;
import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfigFacade;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * DocHelper, version : auto generated on 2025-10-07 09:42:47.833
 */
@Slf4j
public class DocHelper {

     /*
      * FreemarkerDocProcessConfig is thread-safe and should be initialized once for each config file.
      * 
      * Consider using a @ApplicationScoped or Singleton approach.
      */
     private FreemarkerDocProcessConfig docProcessConfig = FreemarkerDocProcessConfigFacade.loadConfigSafe( "cl://venus-sample-i18n/fm-doc-process-config.xml" );

     /**
      * Accessor for FreemarkerDocProcessConfig configuration.
      *
      * @return the FreemarkerDocProcessConfig instance associated with this helper.
      */
     public FreemarkerDocProcessConfig getDocProcessConfig() { return this.docProcessConfig; }

     private static final String PATH_BUNDLE_LABELS = "venus-sample-i18n.i18n.labels";

     public void generateDocument( String handlerId, Locale language, OutputStream os) {
          SafeFunction.apply( () -> {
               // creates the doc helper
               DocHelper docHelper = new DocHelper();
               ResourceBundle labels = ResourceBundle.getBundle( PATH_BUNDLE_LABELS, language );
               // create custom data for the fremarker template 'document.ftl'
               List<People> listPeople = Arrays.asList( new People( "Luthien", "Tinuviel", "Queen" ), new People( "Thorin", "Oakshield", "King" ) );
               String chainId = "document";
               // output generation
               docHelper.getDocProcessConfig().fullProcess( chainId,
                       DocProcessContext.newContext( "listPeople", listPeople )
                               .withAtt( "osName", System.getProperty("os.name") )
                               .withAtt( "javaVersion", System.getProperty("java.version") )
                               .withAtt( "labels", labels ), // adding labels
                                   handlerId, os );
          } );
     }


}
