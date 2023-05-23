# Nutrinexus
Social network for sharing healthy recipes and more

## Web app

https://sve.my.to/nutrinexus

or

http://sve.uk.to/nutrinexus

## Dev mode

in project root folder:

in file `./mvn-vars-dev` change database url, username and password to yours (or create the same scheme and user in your local database),

then

`chmod +x ./mvn.sh`.

to start (with tests):

run

`./mvn.sh dev`,

then go to `localhost:3000` and `localhost:8080`.

after (to stop backend app):

`./mvn.sh dev-stop`.

also

`./mvn.sh backend`

and

`./mvn.sh test`

are available (see `./mvn.sh`).

## Release mode

in project root folder:

copy file `./mvn-vars-dev` to `./mvn-vars-release` and change database url, username and password to yours (your database on your server),

run

`chmod +x ./mvn.sh`

and

`./mvn.sh release`.

then copy `./target/nutrinexus-x.x.x.jar` and `./src/main/frontend/build` to your server and run `nutrinexus-x.x.x.jar` (e.g. with systemd).

finally go to `your.domain/nutrinexus` and `your.domain:8080`.
