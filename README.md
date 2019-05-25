![header](https://n3roo.github.io/img/nero-engine-rect.png)

[![travisCI](https://img.shields.io/travis/N3ROO/javafx-nero-engine/master.svg)](https://travis-ci.org/N3ROO/javafx-nero-engine/) [![Maintainability](https://api.codeclimate.com/v1/badges/93fb4cbc9869d1673d87/maintainability)](https://codeclimate.com/github/N3ROO/javafx-nero-engine/maintainability) [![codecov](https://codecov.io/gh/N3ROO/javafx-nero-engine/branch/master/graph/badge.svg)](https://codecov.io/gh/N3ROO/javafx-nero-engine) [![release](https://img.shields.io/github/release/n3roo/nero-engine.svg)](https://github.com/N3ROO/javafx-nero-engine/releases) [![issues](https://img.shields.io/github/issues/n3roo/javafx-nero-engine.svg)](https://github.com/N3ROO/javafx-nero-engine/issues)

An easy to use java 2D game engine powered by JavaFX 12. 🎮

## 1. Progression

I have a [blog](https://n3roo.github.io/blog.html) on my website where I speak about the last changes.

## 2. Project description

This is my most ambitious project yet. I plan to implement everything that I have learned in Computer Science these last years. I hope that the first version will be released this summer.

**Acknowledgments:**
- Bordeaux Institute of Technology,
- University of Bordeaux.

**Libraries and external services used:**

Core:
- Gluon JavaFX 12: [Website](https://gluonhq.com/products/javafx/), [Docs](https://openjfx.io/index.html)  
- Apache Log4j2 [Website](https://logging.apache.org/log4j/2.x/), [Docs](https://logging.apache.org/log4j/2.0/log4j-api/apidocs/index.html)

Tests (cov & CI):
- Travis CI: [Repo](https://travis-ci.org/N3ROO/javafx-nero-engine/)
- Codecov: [Repo](https://codecov.io/gh/N3ROO/javafx-nero-engine)
- JUnit 5: [Website](https://junit.org/junit5/)

Miscellaneous:
- Java DiscordRPC [Repo](https://github.com/discordapp/discord-rpc), [Docs](https://discordapp.com/developers/docs/rich-presence/how-to)


## 5. Set up your environment

### 5.1 Project import

Fork the project. If you use IntelliJ, you need to open the folder as a project.
Then make sure to right click on:
- "src/main/resources" add "Mark Directory as" -> "Resources root",
- "src/main/java", add "Mark Directory as" -> "Sources root",
- "src/test/java", add "Mark Directory as" -> "Test Sources root",
- "src/demo/java", add "Mark Directory as" -> "Sources root",
- "src/demo/resources", add "Mark Directory as" -> "Resources root".
Also, make sure to use Java 12 (File -> Project Structure -> Project SDK: 12 & Language level: 12 ).

Folders :
- **src/main**: contains the engine source code,
- **src/test**: contains the engine tests,
- **src/demo**: contains a demo game using the engine.

### 5.2 Code coverage & Continuous Integration (CI)

You don't have to do anything concerning the CI.
Regarding code coverage, write `mvn clean test jacoco:report`. Then, from the menu select
Analyze > Show Coverage data. In the new window press the + button and select target/jacoco.exec file.
The test coverage results will appear in the editor Coverage tab.

## 4. Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## 5. Contributors
- N3ROO - *Main developer* - [Github](https://github.com/N3ROO) - [Website](https://n3roo.github.io/)

## 6. License
This project is licensed under the GPL 3.0 License - see the [LICENSE](LICENSE) file for details. In brief, you can do almost anything that you want, except distributing closed source versions.
