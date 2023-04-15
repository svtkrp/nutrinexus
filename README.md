# Nutrinexus
Social network for sharing healthy recipes and more

## Web app

https://sve.my.to/nutrinexus

## Dev mode

in project root folder:

in file `./mvn-vars-dev` change database url, username and password to yours (or create the same scheme and user in your local database)

`chmod +x ./mvn.sh`

to start (with tests):

`./mvn.sh dev`

go to `localhost:3000` and `localhost:8080`

after (to stop backend app):

`./mvn.sh dev-stop`

## Release mode

in project root folder:

copy file `./mvn-vars-dev` to `./mvn-vars-release` and change database url, username and password to yours (your database on your server)

`chmod +x ./mvn.sh`

`./mvn.sh release`

copy `./target/nutrinexus-x.x.x.jar` and `./src/main/frontend/build` to your server and run `nutrinexus-x.x.x.jar` (e.g. with systemd)

go to `your.domain/nutrinexus` and `your.domain:8080`
