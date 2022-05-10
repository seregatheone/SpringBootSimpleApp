# SpringBoot CROC Project

Excel file goes to RestFile controller, which creates DataManagement class. Data routes and data states are 
controlled by DataManagement class. Analytics class is created after file has been parsed. 
Before deploying clone this repository or make sure that it is last version. For deploying you need run `build` 
and `bootRun` commands:

```
./gradlew build
./gradlew bootRun
```

## Auction Requests
`POST /auction` - save information about computed auction \
`GET /auction/{auctionId}` - load auction by auctionId \
`DELETE /auction/{auctionId}` - delete auction by auctionId \
`GET /auctions` - load all auctions

## Settings Request
`POST /settings` - store settings \
`GET /settings` - load settings 

## Wheel Requests
`GET /compute-wheel` - compute wheel lottery analytics\
`POST /wheel-lots` - save information about computed wheel lottery \
`GET /wheel-lots/{wheelId}` - load wheel lottery by wheelId\
`DELETE /wheel-lots/{wheelId}` - delete wheel lottery by wheelId\
`GET /wheel-lots` - load all wheel lottery

## Missions Requests
`GET /compute-mission` - compute missions analytics\
`POST /missions` - save information about computed wheel lottery \
`GET /missions/{wheelId}` - load wheel lottery by wheelId\
`DELETE /missions/{wheelId}` - delete wheel lottery by wheelId\
`GET /missions` - load all wheel lottery

