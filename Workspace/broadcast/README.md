# Broadcast

### Description
Broadcast is a library implemented using [JADE](http://jade.tilab.com/). This project provide an easy implementation of an observer agent and it's a concept proof of applying transparency in multi-agents systems and was developed by the students of Software Engineering at University of Brasilia.

### How to build
  1. Import as "Existing Maven Project"  
  2. Run the "pom.xml" as "Maven clean"  
  3. Build the jar exporting `jade.jar` e `commons-codec-x.x.jar` 
  Obs:. Use the .jar files in "/lib" only if Maven isn't working for you  

### How to use
  1. Import the builted library to your project  
  2. Start a new `Broadcaster` agent  
  3. Targets agents should extends `ObservableAgent` and run the `findBroadcast()` method  
  4. Use `reportThat(String content)` to send messages to the `Broadcaster`    
  5. Now you are able to export all your messages in 3 formats (Plain Text, HTML or JSON), such as ask for a report  

### The 10+ Commandments (For a good development)
  1. Before starting coding, read those rules to avoid problems  
  2. Verify your .gitignore to exclude inconvenient files  
  3. Always pull before starting coding and before pushing any code  
  4. Always commit working and well tested code  
  5. Always commit with an expressive message attached
      (Eg:. Readme updated! Rules section added!)  
  6. Always write code and comments in English (i18n comes later)  
  7. Refactor always as possible  
  8. Don't delete someone else's code, comment instead  
  9. Report your changes in this document  
  10. Add more rules if you consider that are something important missing  

### Collaborators
[Caique Peixoto](http://github.com/caiquerodrigues)  