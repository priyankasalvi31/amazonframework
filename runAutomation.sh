
configFile="src/test/resources/configurations/config.properties"

pomFile="pom.xml"

# Replace values in config.properties
sed -i "s/^BROWSER=.*/BROWSER=$BROWSER/""$configFile"
sed -i "s/^ENV=.*/ENV=$ENV/""$configFile"
sed -i "s/^PARALLEL_MODE=.*/PARALLEL_MODE=$PARALLEL_MODE/""$configFile"
sed -i "s/^MAX_PARALLEL_TESTS=.*/MAX_PARALLEL_TESTS=$MAX_PARALLEL_TESTS/""$configFile"
sed -i "s/^MAX_RETRIES=.*/MAX_RETRIES=$MAX_RETRIES/""$configFile"




sed -i "s|<suiteXmlFile>sanity</suiteXmlFile>|<suiteXmlFile>$SUITE</suiteXmlFile>"|"$pomFile"
sed -i "s|<suiteXmlFile>regression</suiteXmlFile>|<suiteXmlFile>$SUITE</suiteXmlFile>"|"$pomFile"




mvn clean test

ENDLOCAL

