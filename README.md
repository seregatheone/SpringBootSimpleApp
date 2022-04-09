# SpringBootSimpleApp

Excel file goes to RestFile controller, which creates DataManagement class. Data routes and data states are 
controlled by DataManagement class. Analytics class is created after file has been parsed. 
Before deploying clone this repository or make sure that it is last version. For deploying you need run `build` 
and `bootRun` commands:

```
./gradlew build
./gradlew bootRun
```