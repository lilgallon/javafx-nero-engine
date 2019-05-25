package sample.main;

import io.github.n3roo.engine.NeroEngine;
import io.github.n3roo.hud.components.TextHud;
import io.github.n3roo.math.Position;
import io.github.n3roo.world.World;
import javafx.application.Application;
import javafx.stage.Stage;
import sample.entities.Player;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        NeroEngine.init(stage);

        // Discord RPC example
//        Discord discord = Discord.init("576397580620005377");
//        discord.updatePresence("Nero engine", "working on v0.1", -1, -1,
//                    "nero-engine-square", "Large image text",
//                    "", "Small image text",
//                    "", -1, -1, "", "");

        World.addHudComponent(new TextHud("Nero engine", new Position(2, 10)));
        World.addEntity(new Player(new Position(100, 100)));
    }
}
