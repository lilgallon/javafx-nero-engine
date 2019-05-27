package io.github.n3roo.engine;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class NeroEngineTest {

    private Exception exception = null;

    @Start
    void start(Stage stage) {
        try{
            NeroEngine.init(stage);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Test
    void init(){
        assertNull(exception);
    }
}