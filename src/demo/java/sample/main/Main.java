package sample.main;

import io.github.n3roo.discord.Discord;
import io.github.n3roo.engine.NeroEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        NeroEngine.init(stage);

        Discord discord = new Discord();
        discord.updatePresence("Nero engine", "working on v0.1", -1, -1,
                "nero-engine-square", "Large image text",
                "", "Small image text",
                "", -1, -1, "", "");
    }
}
