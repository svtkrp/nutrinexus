# Nutrinexus
Social network for sharing healthy recipes and more

## Dev mode

in project root folder:

in file ./mvn-vars-dev change database url, username and password to yours (or create the same scheme and user in your local database)

chmod +x ./mvn.sh

to start (with tests):

./mvn.sh dev

after (to stop backend app):

./mvn.sh dev-stop

## Release mode

in project root folder:

copy file ./mvn-vars-dev to ./mvn-vars-release and change database url, username and password to yours (on your server)

chmod +x ./mvn.sh

./mvn.sh release

copy ./target/nutrinexus-x.x.x.jar

and

./src/main/frontend/build

to your server and run nutrinexus-x.x.x.jar (e.g. with systemd)
