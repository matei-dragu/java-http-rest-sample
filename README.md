# java-http-rest-sample
Sample HTTP / REST project in Java

### Enable coloured console logging:

#### Microsoft Windows (cmd / IntelliJ console):

Create the following environment variable:

> **SPRING_OUTPUT_ANSI_ENABLED** = **ALWAYS**

#### MacOS (terminal):

Create the following environment variable in **_~/.bashrc_**, **_~/.bash_profile_** or **_~/.profile_**: 

> export SPRING_OUTPUT_ANSI_ENABLED=ALWAYS

 #### MacOS (IntelliJ console):

Add the environment variable above to the default settings of the Run/Debug configurations used for running the 
application or JUnit tests. This is a necessary step as IntelliJ dows not take into account the environment variables
defined in **_~/.bashrc_**, **_~/.bash_profile_** or **_~/.profile_**

## Other information 

Command used to generate project:

    gradle init --type java-application
